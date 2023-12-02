package com.example.oneentry.network

import com.example.oneentry.model.ProductsFilter
import com.example.oneentry.network.core.OneEntryCore
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntryProductsTest {

    private lateinit var provider: OneEntryProducts

    @Before
    fun setUp() {

        OneEntryCore.initializeApp(
            "https://hummel-mobile.oneentry.cloud",
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiS290bGluIFNkayIsInNlcmlhbE51bWJlciI6MiwiaWF0IjoxNzAwMjE3ODU2LCJleHAiOjE3MzE3NTM4NDR9.0F4D0rgAM9nqpFEpbJqxiUaNNxik_wpI70QPFXoYSzk"
        )
        provider = OneEntryProducts.instance
    }

    @Test
    fun testProducts() = runBlocking {

        val result = provider.products(langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testEmptyPageProducts() = runBlocking {

        val result = provider.emptyPageProducts(langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testProductsByPageId() = runBlocking {

        val result = provider.products(
            pageId = 1,
            offset = 0,
            limit = 30,
            langCode = "en_US",
            sortKey = null,
            sortOrder = null
        )

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testProductsByPageUrl() = runBlocking {

        val result = provider.products(
            url = "heroes",
            offset = 0,
            limit = 30,
            langCode = "en_US",
            sortKey = null,
            sortOrder = null
        )

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testRelatedProducts() = runBlocking {

        val result = provider.relatedProducts(id = 8, langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testProductById() = runBlocking {

        val result = provider.products(id = 8, langCode = "en_US")

        assertEquals("iPhone 14 Pro", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun testFilterProducts() = runBlocking {

        val filter = listOf(
            ProductsFilter(
                attributeMarker = "price",
                conditionMarker = "lth",
                conditionValue = 260,
                pageId = 1
            )
        )

        val result = provider.filterProducts(filter, langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testQuickSearch() = runBlocking {

        val result = provider.quickSearch(name = "cat", langCode = "en_US")

        assertEquals("Cat1", result.first().title)
    }

    @Test
    fun testProductStatuses() = runBlocking {

        val result = provider.productStatuses()

        assertEquals("Storage", result.first().localizeInfos["en_US"]?.title)
    }

    @Test
    fun testProductStatus() = runBlocking {

        val result = provider.productStatus(id = 2)

        assertEquals("Storage", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun testProductStatusByMarker() = runBlocking {

        val result = provider.productStatus(marker = "storage")

        assertEquals("Storage", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun productStatusMarkerValidation() = runBlocking {

        val result = provider.productStatusMarkerValidation(marker = "storage")

        assertEquals(true, result)
    }
}