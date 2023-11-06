package com.example.oneentry.network

import com.example.oneentry.model.ProductsFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class OneEntryProductsTest {

    private lateinit var provider: OneEntryProducts
    @Before
    fun setUp() {

        OneEntryCore.initializeApp("https://testproject.oneentry.cloud")
        provider = OneEntryProducts()
    }

    @Test
    fun testProducts() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.products(langCode = "en_US")

            assertEquals(result.total, result.items.count())
        }
    }

    @Test
    fun emptyPageProducts() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.emptyPageProducts(langCode = "en_US")

            assertEquals(result.total, result.items.count())
        }
    }

    @Test
    fun testProductsByPageId() {

        CoroutineScope(Dispatchers.IO).launch {

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
    }

    @Test
    fun testProductsByPageUrl() {

        CoroutineScope(Dispatchers.IO).launch {

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
    }

    @Test
    fun relatedProducts() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.relatedProducts(id = 5, langCode = "en_US")

            assertEquals(result.total, result.items.count())
        }
    }

    @Test
    fun testProductById() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.products(id = 3, langCode = "en_US")

            assertEquals("Ninja", result.localizeInfos["en_US"]?.title)
        }
    }

    @Test
    fun filterProducts() {

        CoroutineScope(Dispatchers.IO).launch {

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
    }

    @Test
    fun quickSearch() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.quickSearch(name = "cat", langCode = "en_US")

            assertEquals("cat", result[0].title)
        }
    }

    @Test
    fun productStatuses() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.productStatuses()

            assertEquals("In Stock", result[0].localizeInfos["en_US"]?.title)
        }
    }

    @Test
    fun productStatus() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.productStatus(id = 5)

            assertEquals("In Stock", result.localizeInfos["en_US"]?.title)
        }
    }

    @Test
    fun testProductStatus() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.productStatus(marker = "inStock")

            assertEquals("In Stock", result.localizeInfos["en_US"]?.title)
        }
    }

    @Test
    fun productStatusMarkerValidation() {

        CoroutineScope(Dispatchers.IO).launch {

            val result = provider.productStatusMarkerValidation(marker = "inStock")

            assertEquals(true, result)
        }
    }
}