package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntrySystemTest {

    private lateinit var provider: OneEntrySystem

    @Before
    fun setUp() {

        OneEntryCore.initializeApp("https://hummel-mobile.oneentry.cloud")
        provider = OneEntrySystem.instance
    }

    @Test
    fun test404() = runBlocking {

        val result = provider.test404()

        assertEquals(404, result.statusCode)
    }

    @Test
    fun test500() = runBlocking {

        val result = provider.test500()

        assertEquals(500, result.statusCode)
    }
}