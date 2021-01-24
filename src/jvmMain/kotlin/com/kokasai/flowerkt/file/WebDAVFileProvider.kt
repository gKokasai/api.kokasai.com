package com.kokasai.flowerkt.file

import io.ktor.client.HttpClient
import io.ktor.client.content.LocalFileContent
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.util.toByteArray
import java.io.File

class WebDAVFileProvider<T : HttpClientEngineConfig>(
    clientEngine: HttpClientEngineFactory<T>,
    private val webdavUsername: String,
    private val webdavPassword: String,
    private val webdavUrl: String
) : RemoteFileProvider {
    private val cacheFile = mutableMapOf<String, File?>()

    private val client = HttpClient(clientEngine) {
        install(Auth) {
            basic {
                username = webdavUsername
                password = webdavPassword
            }
        }
        expectSuccess = false
    }

    override suspend fun add(path: String, file: File): Boolean {
        cacheFile[path] = file
        return client.request<HttpResponse>("$webdavUrl/$path") {
            method = HttpMethod.Put
            body = LocalFileContent(file)
        }.status.isSuccess()
    }

    override suspend fun remove(path: String): Boolean {
        cacheFile.remove(path)
        return client.request<HttpResponse>("$webdavUrl/$path") {
            method = HttpMethod.Delete
        }.status.isSuccess()
    }

    override suspend fun get(path: String): File? {
        val extension = path.substringAfterLast('.', "")
        if (extension.isBlank()) return null
        return cacheFile.getOrPut(path) {
            val response = client.request<HttpResponse>("$webdavUrl/$path") {
                method = HttpMethod.Get
            }
            if (response.status.isSuccess()) {
                File.createTempFile("tmp", ".$extension").apply {
                    writeBytes(response.content.toByteArray())
                }
            } else {
                null
            }
        }
    }
}
