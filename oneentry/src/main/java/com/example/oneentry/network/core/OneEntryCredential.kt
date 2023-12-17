package com.example.oneentry.network.core

import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import java.io.File
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class OneEntryCredential {

    var headers: HeadersBuilder
    lateinit var trustManager: X509TrustManager

    constructor(token: String) {

        headers = HeadersBuilder().apply {
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append("x-app-token", token)
        }
    }

    constructor(certificateName: String, password: String, filePath: String) {

        val file = File(filePath)

        if (!file.exists())
            throw OneEntryCredentialException.certificateNotFound(certificateName)

        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(file.inputStream(), password.toCharArray())

        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)

//        val sslContext = SSLContext.getInstance("TLC")
//        sslContext.init(null, trustManagerFactory.trustManagers, null)

        trustManager = trustManagerFactory.trustManagers.first { it is X509TrustManager } as X509TrustManager

        val alias = keyStore.aliases().nextElement()
        val key = keyStore.getKey(alias, password.toCharArray())
        val certificate = keyStore.getCertificate(alias)


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