package com.kokasai.api.group

import com.kokasai.api.KokasaiAPI

data class Group(val name: String) {
    var file: GroupFile? = null
        private set

    private val filePath = "group/$name.json"

    suspend fun load() {
        file = KokasaiAPI.fileProvider.get(filePath)?.let(GroupFile::from)
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
        suspend fun get(name: String) = Group(name).apply {
            load()
        }
    }
}
