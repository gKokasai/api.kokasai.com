package com.kokasai.api.user

import com.kokasai.api.KokasaiAPI

data class User(val name: String) {
    var file: UserFile? = null
        private set

    private val filePath = "user/$name.json"

    suspend fun load() {
        file = KokasaiAPI.fileProvider.get(filePath)?.let(UserFile::from)
    }

    suspend fun save() {
        val file = file?.toFile()
        if (file != null) {
            KokasaiAPI.fileProvider.add(filePath, file)
        } else {
            KokasaiAPI.fileProvider.remove(filePath)
        }
    }

    companion object {
        suspend fun get(name: String) = User(name).apply {
            load()
        }
    }
}
