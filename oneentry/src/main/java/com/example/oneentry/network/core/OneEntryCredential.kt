package com.example.oneentry.network.core

import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import java.io.File
import java.security.KeyStore

class OneEntryCredential {

    var headers: HeadersBuilder
    lateinit var keyStore: KeyStore
    lateinit var password: String

    constructor(token: String) {

        headers = HeadersBuilder().apply {
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append("x-app-token", token)
        }
    }

    constructor(certificateName: String, password: String, filePath: String) {

        val file = File(filePath)
        this.password = password

        if (!file.exists())
            throw OneEntryCredentialException.certificateNotFound(certificateName)

        keyStore = KeyStore.getInstance("pkcs12")
        keyStore.load(file.inputStream(), password.toCharArray())

        headers = HeadersBuilder().apply {
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