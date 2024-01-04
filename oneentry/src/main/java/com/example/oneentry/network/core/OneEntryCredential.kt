package com.example.oneentry.network.core

import com.example.oneentry.model.OneEntryException
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.network.tls.addKeyStore
import io.ktor.serialization.kotlinx.json.json
import java.io.File
import java.security.KeyStore

class OneEntryCredential {

    private lateinit var keyStore: KeyStore
    private lateinit var password: String

    lateinit var client: HttpClient
    val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryCredential = OneEntryCredential()
    }

    internal fun generateHttpClient() {

        client = HttpClient(CIO) {

            expectSuccess = true

            install(ContentNegotiation) {
                json(core.serializer)
            }

            engine {
                https {
                    addKeyStore(keyStore, password.toCharArray())
                }
            }

            HttpResponseValidator {
                handleResponseExceptionWithRequest { exception, _ ->
                    val responseException = exception as? ResponseException ?: return@handleResponseExceptionWithRequest
                    val response = responseException.response
                    val oneEntryException = core.serializer.decodeFromString<OneEntryException>(response.bodyAsText())
                    throw oneEntryException
                }
            }
        }
    }

    fun credentialToken(token: String): HeadersBuilder {

        return HeadersBuilder().apply {
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append("x-app-token", token)
        }
    }

    fun credentialAuth(certificateName: String, password: String, filePath: String): HeadersBuilder {

        val file = File(filePath)
        this.password = password

        if (!file.exists())
            throw OneEntryCredentialException.certificateNotFound(certificateName)

        keyStore = KeyStore.getInstance("pkcs12")
        keyStore.load(file.inputStream(), password.toCharArray())

        return HeadersBuilder().apply {
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
    }
}

class OneEntryCredentialException(message: String): Exception(message) {

    companion object {

        fun certificateNotFound(name: String): OneEntryCredentialException {
            return OneEntryCredentialException("Certificate with name $name.p12 not found")
        }
    }
}