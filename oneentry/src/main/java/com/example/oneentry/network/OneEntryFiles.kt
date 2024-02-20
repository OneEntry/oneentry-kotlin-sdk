package com.example.oneentry.network

import com.example.oneentry.model.OneEntryFile
import com.example.oneentry.network.core.OneEntryCore
import com.example.oneentry.network.core.append
import io.ktor.client.call.body
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import java.io.File

class OneEntryFiles private constructor() {

    private val core = OneEntryCore.instance

    companion object {

        val instance: OneEntryFiles = OneEntryFiles()
    }

    suspend fun uploadFile(
        fileUrl: String,
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
                parameters.append("type", type)
                parameters.append("entity", entity)
                parameters.append("id", id)
                parameters.append("width", width)
                parameters.append("height", height)
                parameters.append("compress", compress)
            }
            setBody(
                MultiPartFormDataContent(
                    formData {
                        append("files", File(fileUrl).readBytes(), Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=${fileUrl.substring(fileUrl.lastIndexOf("\\") + 1)}")
                        })
                    }
                )
            )
            onUpload { bytesSentTotal, contentLength ->
                println("Sent $bytesSentTotal bytes from $contentLength")
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
                parameters.append("filename", name)
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
                parameters.append("filename", name)
                parameters.append("type", type)
                parameters.append("entity", entity)
                parameters.append("id", id)
            }
        }
    }
}