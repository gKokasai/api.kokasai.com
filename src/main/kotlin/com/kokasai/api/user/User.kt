package com.kokasai.api.user

import com.kokasai.api.KokasaiAPI
import com.kokasai.api.group.Group

data class User(val name: String) {
    lateinit var file: UserFile
        private set

    private val filePath = "user/$name.json"

    suspend fun load() {
        file = KokasaiAPI.fileProvider.get(filePath)?.let(UserFile::from) ?: UserFile()
    }

    suspend fun save() {
        val file = file.toFile()
        KokasaiAPI.fileProvider.add(filePath, file)
    }

    companion object {
        suspend fun isAdmin(name: String) = get(name).file.group.contains(Group.Name.admin)

        suspend fun get(name: String) = User(name).apply {
            load()
        }
    }
}
