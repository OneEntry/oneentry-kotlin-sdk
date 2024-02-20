package com.example.oneentry.network

import com.example.oneentry.model.OneEntryMenu
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class OneEntryMenuTest {

    private val provider = OneEntryProject.instance
    private lateinit var menu: OneEntryMenu

    @Before
    fun setUp() = runBlocking {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
    }

    @Test
    fun testMenu() = runBlocking {

        menu = provider.menu("dev")

        assertEquals("dev", menu.identifier)
        assertNotNull(menu.pages)
    }

    @After
    fun testTree() = runBlocking {

        menu.pages.forEach {

            assertNotNull(it.children)
        }
    }
}