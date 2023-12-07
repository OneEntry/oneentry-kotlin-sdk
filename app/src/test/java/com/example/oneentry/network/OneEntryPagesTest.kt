package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class OneEntryPagesTest {

    private val provider = OneEntryPages.instance

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
    }

    @Test
    fun testRootPages() = runBlocking {

        val result = provider.rootPages("en_US")

        assertEquals("heroes", result.first().pageUrl)
    }

    @Test
    fun testCatalogPages() = runBlocking {

        val result = provider.catalogPages("en_US")

        assertEquals("heroes", result.first().pageUrl)
    }

    @Test
    fun testPages() = runBlocking {

        val result = provider.pages("en_US")

        assertEquals("heroes", result.first().pageUrl)
    }

    @Test
    fun testPageId() = runBlocking {

        val result = provider.page(1, "en_US")

        assertEquals(null, result.localizeInfos?.get("en_US")?.contentAsLocalizeInfo)
    }

    @Test
    fun testPageUrl() = runBlocking {

        val result = provider.page("heroes", "en_US")

        assertEquals("heroes", result.pageUrl)
    }

    @Test
    fun testConfig() = runBlocking {

        val result = provider.config("heroes")

        assertEquals(null, result.rowsPerPage)
    }

    @Test
    fun testQuickSearch() = runBlocking {

        val result = provider.quickSearch("heroes", "en_US")

        assertEquals(1, result.first().id)
    }
}