package com.example.oneentry.network

import com.example.oneentry.model.OneEntryException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntrySystemTest {

    private lateinit var provider: OneEntrySystem

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
        provider = OneEntrySystem.instance
    }

    @Test
    fun test404() = runBlocking {
        try {
            provider.test404()
        } catch (error: OneEntryException) {
            assertEquals(404, error.statusCode)
        }
    }

    @Test
    fun test500() = runBlocking {
        try {
            provider.test500()
        } catch (error: OneEntryException) {
            assertEquals(500, error.statusCode)
        }
    }
}