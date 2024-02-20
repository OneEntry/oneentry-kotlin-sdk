package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse

class OneEntryProjectTest {

    private val provider = OneEntryProject.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
    }

    @Test
    fun testAdmins() = runBlocking {

        val result = provider.admins(langCode = "en_US")

        assertFalse(result.isEmpty())
    }

    @Test
    fun testBlocks() = runBlocking {

        val result = provider.blocks(langCode = "en_US")

        assertFalse(result.isEmpty())
    }

    @Test
    fun testBlockWithMarker() = runBlocking {

        val result = provider.block("dev", "en_US")

        assertEquals("dev", result.identifier)
    }

    @Test
    fun testGeneralTypes() = runBlocking {

        val result = provider.generalTypes()

        assertFalse(result.isEmpty())
    }

    @Test
    fun testLocales() = runBlocking {

        val result = provider.locales()

        assertEquals("en_US", result.first().code)
    }
}