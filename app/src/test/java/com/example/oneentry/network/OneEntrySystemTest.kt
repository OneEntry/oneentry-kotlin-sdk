package com.example.oneentry.network

import com.example.oneentry.model.OneEntryError
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntrySystemTest {

    private lateinit var provider: OneEntrySystem
    private val serializer = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Before
    fun setUp() {

        OneEntryCore.initializeApp("https://hummel-mobile.oneentry.cloud")
        provider = OneEntrySystem.instance
    }

    @Test
    fun test404() = runBlocking {
        try {
            provider.test404()
        } catch (e: Exception) {
            val error = serializer.decodeFromString<OneEntryError>(e.toString())
            assertEquals(404, error.statusCode)
        }
    }

    @Test
    fun test500() = runBlocking {
        try {
            provider.test500()
        } catch (e: Exception) {
            val error = serializer.decodeFromString<OneEntryError>(e.toString())
            assertEquals(500, error.statusCode)
        }
    }
}