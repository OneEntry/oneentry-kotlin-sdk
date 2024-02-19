package com.example.oneentry.network

import com.example.oneentry.network.core.OneEntryCertificateCredential
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.OneEntryTokenCredential

class TestConfig {

    private val domain = "hummel-mobile.oneentry.cloud"
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
    private val password = "WASALWASAL123"
    private val filePath = "C:\\Users\\vip\\IdeaProjects\\kotlin\\oneentry\\src\\main\\java\\com\\example\\oneentry\\config\\certificate.p12"

    enum class AuthType {
        TOKEN,
        CERTIFICATE
    }

    fun configure(authType: AuthType) {

        val credential = when(authType) {
            AuthType.TOKEN -> OneEntryTokenCredential(token)
            AuthType.CERTIFICATE -> OneEntryCertificateCredential(filePath, password)
        }

        OneEntryCore.initializeApp(domain, credential)
    }
}