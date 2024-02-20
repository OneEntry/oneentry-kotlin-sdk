package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class OneEntryTemplatePreviewsTest {

    private val provider = OneEntryTemplatePreviews.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
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