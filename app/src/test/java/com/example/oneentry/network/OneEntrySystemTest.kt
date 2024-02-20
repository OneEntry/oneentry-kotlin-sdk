package com.example.oneentry.network

import com.example.oneentry.model.OneEntryException
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntrySystemTest {

    private val provider = OneEntrySystem.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
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