package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class OneEntryTemplatePreviewsTest {

    private val provider = OneEntryTemplatePreviews.instance

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
    }

    @Test
    fun testPreviews() = runBlocking {

        val result = provider.templates()

        assertFalse(result.isEmpty())
    }

    @Test
    fun testPreviewWithID() = runBlocking {

        val result = provider.template(1)

        assertEquals("preview", result.identifier)
    }

    @Test
    fun testPreviewWithMarker() = runBlocking {

        val result = provider.template("preview")

        assertEquals("preview", result.identifier)
    }
}