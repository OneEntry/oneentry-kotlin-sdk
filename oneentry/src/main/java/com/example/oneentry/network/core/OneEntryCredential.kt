package com.example.oneentry.network.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.network.tls.addKeyStore
import java.io.File
import java.security.KeyStore

interface OneEntryCredential {
    val client: HttpClient
}

class TokenCredential(private val token: String): OneEntryCredential {

    override val client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            header("x-app-token", token)
        }
    }
}

class CertificateCredential(
    private val path: String,
    private val password: String
): OneEntryCredential {

    override val client: HttpClient = HttpClient(CIO) {
        engine {
            https {
                println("KeyStore: $keyStore")
                println("Password: $password")
                addKeyStore(keyStore, password.toCharArray())
            }
        }
    }

    private val keyStore: KeyStore
        get() {

            val file = File(path)

            if (!file.exists()) {
                throw OneEntryCredentialException.certificateNotFound(path.substring(path.lastIndexOf("\\") + 1))
            }

            val keyStore = KeyStore.getInstance("pkcs12")
            keyStore.load(file.inputStream(), password.toCharArray())

            return keyStore
        }
}

class OneEntryCredentialException(message: String): Exception(message) {

    companion object {

        fun certificateNotFound(name: String): OneEntryCredentialException {
            return OneEntryCredentialException("Certificate with name $name not found")
        }
    }
}