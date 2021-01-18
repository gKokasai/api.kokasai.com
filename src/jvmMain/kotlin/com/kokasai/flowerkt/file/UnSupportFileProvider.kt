package com.kokasai.flowerkt.file

import java.io.File

object UnSupportFileProvider : FileProvider {
    override suspend fun add(path: String, file: File): Boolean {
        return false
    }

    override suspend fun remove(path: String): Boolean {
        return false
    }

    override suspend fun get(path: String): File? {
        return null
    }
}
