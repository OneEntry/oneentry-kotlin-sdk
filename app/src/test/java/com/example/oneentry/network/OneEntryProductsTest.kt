package com.example.oneentry.network

import com.example.oneentry.model.ProductsFilter
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class OneEntryProductsTest {

    private lateinit var provider: OneEntryProducts

    @Before
    fun setUp() {

        OneEntryCore.initializeApp("https://testproject.oneentry.cloud")
        provider = OneEntryProducts.instance
    }

    @Test
    fun testProducts() = runBlocking {

        val result = provider.products(langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun emptyPageProducts() = runBlocking {

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
    fun relatedProducts() = runBlocking {

        val result = provider.relatedProducts(id = 5, langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun testProductById() = runBlocking {

        val result = provider.products(id = 3, langCode = "en_US")

        assertEquals("Ninja", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun filterProducts() = runBlocking {

        val filter = listOf(
            ProductsFilter(
                attributeMarker = "price",
                conditionMarker = "lth",
                conditionValue = 160,
                pageId = 1
            )
        )

        val result = provider.filterProducts(filter, langCode = "en_US")

        assertEquals(result.total, result.items.count())
    }

    @Test
    fun quickSearch() = runBlocking {

        val result = provider.quickSearch(name = "cat", langCode = "en_US")

        assertEquals("Cat", result.first().title)
    }

    @Test
    fun productStatuses() = runBlocking {

        val result = provider.productStatuses()

        assertEquals("In Stock", result.first().localizeInfos["en_US"]?.title)
    }

    @Test
    fun productStatus() = runBlocking {

        val result = provider.productStatus(id = 5)

        assertEquals("In Stock", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun testProductStatus() = runBlocking {

        val result = provider.productStatus(marker = "inStock")

        assertEquals("In Stock", result.localizeInfos["en_US"]?.title)
    }

    @Test
    fun productStatusMarkerValidation() = runBlocking {

        val result = provider.productStatusMarkerValidation(marker = "inStock")

        assertEquals(true, result)
    }
}