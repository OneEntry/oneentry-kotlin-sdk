package com.example.oneentry.network.core

import io.ktor.http.ContentType
import io.ktor.http.HeadersBuilder
import java.io.File
import java.security.KeyFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.spec.PKCS8EncodedKeySpec
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

class OneEntryCredential {

    internal lateinit var headers: HeadersBuilder
    internal var asUrlCredential: String? = null

    constructor(token: String) {

        asUrlCredential = token
        headers.apply {
            ContentType.Application.Json
            append("x-app-token", token)
        }
    }

    constructor(name: String, password: String, filePath: String) {

        val file = File(filePath)

        if (!file.exists())
            throw OneEntryCredentialException.certificateNotFound(name)

        val data = file.readBytes()
        val keyStore = KeyStore.getInstance("PKCS12")
        keyStore.load(null, null)

        val key = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(data))
        val certificate = CertificateFactory.getInstance("X.509").generateCertificate(file.inputStream())

        val keyEntry = KeyStore.PrivateKeyEntry(key, arrayOf(certificate))
        keyStore.setEntry("key", keyEntry, KeyStore.PasswordProtection(password.toCharArray()))

        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, password.toCharArray())

        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(keyStore)

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(keyManagerFactory.keyManagers, trustManagerFactory.trustManagers, null)

        asUrlCredential = ""
        headers.apply { ContentType.Application.Json }
    }
}

class OneEntryCredentialException(message: String): Exception(message) {

    companion object {

        fun certificateNotFound(name: String): OneEntryCredentialException {
            return OneEntryCredentialException("Certificate with name $name.p12 not found")
        }
    }
}