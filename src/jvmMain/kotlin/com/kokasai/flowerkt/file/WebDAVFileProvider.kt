package com.kokasai.flowerkt.file

import io.ktor.client.*
import io.ktor.client.content.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import java.io.*

class WebDAVFileProvider(val client: HttpClient, val url: String): FileProvider {
    private val cacheFile = mutableMapOf<String, File?>()

    override suspend fun add(path: String, file: File): Boolean {
        cacheFile[path] = file
        return client.request<HttpResponse>("$url/$path") {
            method = HttpMethod.Put
            body = LocalFileContent(file)
        }.status.isSuccess()
    }

    override suspend fun remove(path: String): Boolean {
        cacheFile.remove(path)
        return client.request<HttpResponse>("$url/$path") {
            method = HttpMethod.Delete
        }.status.isSuccess()
    }

    override suspend fun get(path: String): File? {
        val extension = path.substringAfterLast('.', "")
        if (extension.isBlank()) return null
        return cacheFile.getOrPut(path) {
            val response = client.request<HttpResponse>("$url/$path") {
                method = HttpMethod.Get
            }
            if (response.status.isSuccess()) {
                createTempFile(suffix = ".$extension").apply {
                    writeBytes(response.content.toByteArray())
                }
            } else {
                null
            }
        }
    }
}