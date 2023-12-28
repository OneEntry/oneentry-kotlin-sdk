package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFormData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntryFormsTest {

    private lateinit var provider: OneEntryForms

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
        provider = OneEntryForms.instance
    }

    @Test
    fun testForms() = runBlocking {

        val result = provider.forms(langCode = "en_US")

        assertEquals("auth", result.first().identifier)
    }

    @Test
    fun testFormWithMarker() = runBlocking {

        val result = provider.form("auth", "en_US")

        assertEquals("auth", result.identifier)
    }

    @Test
    fun testFormDataCreation() = runBlocking {

        val data: Map<String, List<OneEntryFormData>> = mapOf(
            "en_US" to listOf(
                OneEntryFormData("login", "Dino"),
                OneEntryFormData("password", "544")
            )
        )

        val result = provider.sendData("auth", data)
    }

    @Test
    fun testFormData() = runBlocking {

        val result = provider.data()

        assertEquals(result.items.count(), result.total)
    }

    @Test
    fun testFormDataByMarker() = runBlocking {

        val result = provider.data("auth")

        assertEquals(result.items.count(), result.total)
    }
}