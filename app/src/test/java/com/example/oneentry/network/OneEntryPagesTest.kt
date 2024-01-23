package com.example.oneentry.network

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse

class OneEntryPagesTest {

    private val provider = OneEntryPages.instance

    @Before
    fun setUp() {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)
    }

    @Test
    fun testRootPages() = runBlocking {

        val result = provider.rootPages("en_US")

        assertEquals("heroes", result.first().pageUrl)
    }

    @Test
    fun testCatalogPages() = runBlocking {

        val result = provider.catalogPages("en_US")

        assertEquals("xml", result.first().pageUrl)
    }

    @Test
    fun testPages() = runBlocking {

        val result = provider.pages("en_US")

        println(result)

        assertEquals("xml", result.first().pageUrl)
    }

    @Test
    fun testPageId() = runBlocking {

        val result = provider.page(9, "en_US")

        assertEquals("space", result.pageUrl)
    }

    @Test
    fun testPageUrlChildren() = runBlocking {

        val result = provider.pagesChildren("xml", "en_US")

        assertFalse(result.isNotEmpty())
    }

    @Test
    fun testPageUrl() = runBlocking {

        val result = provider.page("heroes", "en_US")

        assertEquals("heroes", result.pageUrl)
    }

    @Test
    fun testPageUrlForms() = runBlocking {

        val result = provider.pagesForms("xml", "en_US")

        assertFalse(result.isNotEmpty())
    }

    @Test
    fun testPageUrlBlocks() = runBlocking {

        val result = provider.pagesBlocks("xml", "en_US")

        assertFalse(result.isNotEmpty())
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