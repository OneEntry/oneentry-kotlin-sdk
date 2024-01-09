package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

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

        assertFalse(result.isNotEmpty())
    }

    @Test
    fun testBlockWithMarker() = runBlocking {

        val result = provider.block("test", "en_US")

        assertEquals("test", result.identifier)
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

    @Test
    fun testMenu() = runBlocking {

        val result = provider.menu("categories")

        assertEquals("categories", result.identifier)
    }
}