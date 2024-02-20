package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFile
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertNotNull

class OneEntryFilesTest {

    private lateinit var file: OneEntryFile
    private lateinit var filename: String

    private val provider = OneEntryFiles.instance
    private val type = "page"
    private val entity = "editor"
    private val id = 3787

    @Before
    fun setUp() = runBlocking {

        TestConfig().configure(TestConfig.AuthType.CERTIFICATE)

        val fileUrl = "C:\\Users\\vip\\Desktop\\Dinar\\LoveDinar.png"

        val result = provider.uploadFile(fileUrl, type, entity, id)

        file = result.first()
        filename = file.filename.substring(file.filename.lastIndexOf("/") + 1)
    }

    @Test
    fun testFile() = runBlocking {

        val result = provider.file(filename, type, entity, id)

        assertNotNull(result)
    }

    @After
    fun tearDown() = runBlocking {

        val result = provider.deleteFile(filename, type, entity, id)

        assertNotNull(result)
    }
}