package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import org.junit.Test

class OneEntryProjectTest {

    private val provider = OneEntryProject.instance

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
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

        assertEquals("", result.identifier)
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