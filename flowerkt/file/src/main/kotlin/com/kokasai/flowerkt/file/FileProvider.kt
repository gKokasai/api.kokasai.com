package com.kokasai.flowerkt.file

import java.io.File

interface FileProvider {
    suspend fun add(path: String, file: File): Boolean
    suspend fun remove(path: String): Boolean
    suspend fun get(path: String): File?
}
