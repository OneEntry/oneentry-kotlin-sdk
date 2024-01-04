package com.example.oneentry.network

import com.example.oneentry.network.core.OneEntryCore

class TestConfig {

    private val domain = "https://hummel-mobile.oneentry.cloud"
    private val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"

    private val certificateName = "certificate"
    private val password = "WASALWASAL123"
    private val filePath = "C:\\Users\\vip\\AndroidStudioProjects\\kotlin\\oneentry\\src\\main\\java\\com\\example\\oneentry\\config\\certificate.p12"

    enum class AuthType {
        TOKEN,
        CERTIFICATE
    }

    fun configure(authType: AuthType) {

        when (authType) {

            AuthType.TOKEN -> OneEntryCore.initializeApp(domain, token)
            AuthType.CERTIFICATE -> OneEntryCore.initializeApp(domain, certificateName, password, filePath)
        }
    }
}