package com.kokasai.flowerkt.file

import java.io.File

class LocalFileProvider(val directory: File) : FileProvider {
    override suspend fun add(path: String, file: File): Boolean {
        return try {
            file.copyTo(File(directory, path))
            true
        } catch (ex: Exception) {
            ex.printStackTrace()
            false
        }
    }

    override suspend fun remove(path: String): Boolean {
        return File(directory, path).delete()
    }

    override suspend fun get(path: String): File? {
        val file = File(directory, path)
        return if (file.exists()) file else null
    }
}
