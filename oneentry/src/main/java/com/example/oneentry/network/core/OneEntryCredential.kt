package com.example.oneentry.network.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.network.tls.addKeyStore
import java.io.FileInputStream
import java.security.KeyStore

interface OneEntryCredential {
    val client: HttpClient
}

class OneEntryTokenCredential(private val token: String): OneEntryCredential {

    override val client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            header("x-app-token", token)
        }
    }
}

class OneEntryCertificateCredential(
    private val path: String,
    private val password: String
): OneEntryCredential {

    override val client: HttpClient = HttpClient(CIO) {
        engine {
            https {
                addKeyStore(keyStore, password.toCharArray())
            }
        }
        defaultRequest {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

    private val keyStore: KeyStore
        get() {

            val file = FileInputStream(path)
            val keyStore = KeyStore.getInstance("pkcs12")
            keyStore.load(file, password.toCharArray())

            return keyStore
        }
}
