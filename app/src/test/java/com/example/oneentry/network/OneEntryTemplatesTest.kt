package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class OneEntryTemplatesTest {

    private val provider = OneEntryTemplates.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
    }

    @Test
    fun testTemplates() = runBlocking {

        val result = provider.templates("forCatalogProducts")

        assertFalse(result.isEmpty())
    }

    @Test
    fun testAllTemplates() = runBlocking {

        val result = provider.templates()

        assertEquals("forCatalogProducts", result.forCatalogProducts.first().generalTypeName)
    }
}