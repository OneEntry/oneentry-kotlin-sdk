package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFile
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body
import io.ktor.http.HttpMethod

class OneEntryFiles private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryFiles = OneEntryFiles()
    }

    suspend fun uploadFile(
        fileURL: String,
        type: String,
        entity: String,
        id: Int,
        width: Int? = null,
        height: Int? = null,
        compress: Boolean = false
    ): List<OneEntryFile> {

        val response = core.requestItems("files") {
            method = HttpMethod.Post
            url {
                parameters.append("fileURL", fileURL)
                parameters.append("type", type)
                parameters.append("entity", entity)
                parameters.append("id", id)
                parameters.append("width", width)
                parameters.append("height", height)
                parameters.append("compress", compress)
            }
        }

        return response.body()
    }

    suspend fun file(
        name: String,
        type: String,
        entity: String,
        id: Int
    ) {

        core.requestItems("files") {
            url {
                parameters.append("name", name)
                parameters.append("type", type)
                parameters.append("entity", entity)
                parameters.append("id", id)
            }
        }
    }

    suspend fun deleteFile(
        name: String,
        type: String,
        entity: String,
        id: Int
    ) {

        core.requestItems("files") {
            method = HttpMethod.Delete
            url {
                parameters.append("name", name)
                parameters.append("type", type)
                parameters.append("entity", entity)
                parameters.append("id", id)
            }
        }
    }
}