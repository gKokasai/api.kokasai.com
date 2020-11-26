package com.kokasai.flowerkt.file

import io.ktor.client.*
import io.ktor.http.*
import java.io.*

class WebDAV(val client: HttpClient, val url: Url): FileProvider {
    override fun add(path: String, file: File) {

    }

    override fun remove(path: String) {

    }

    override fun get(path: String) {

    }
}