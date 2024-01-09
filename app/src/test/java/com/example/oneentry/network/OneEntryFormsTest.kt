package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFormData
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntryFormsTest {

    private val provider = OneEntryForms.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
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

        assertEquals(result.total, result.items.count())
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