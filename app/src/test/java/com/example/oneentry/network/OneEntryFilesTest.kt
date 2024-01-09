package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFile
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class OneEntryFilesTest {

    private lateinit var file: OneEntryFile
    private lateinit var filename: String


    private val provider = OneEntryFiles.instance
    private val type = "page"
    private val entity = "test"
    private val id = 15

    @Before
    fun setUp() = runBlocking {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)

        val url = "C:\\Users\\vip\\Desktop\\Dinar\\AngryDinar.png"
        val result = provider.uploadFile(url, type, entity, id)

        file = result.first()
        filename = file.filename
    }

    @Test
    fun testFile() = runBlocking {

        val result = provider.file(filename, type, entity, id)

        assertNotNull(result)
    }

    @Test
    fun testDeleteFile() = runBlocking {

        val result = provider.deleteFile(filename, type, entity, id)

        assertNotNull(result)
    }
}