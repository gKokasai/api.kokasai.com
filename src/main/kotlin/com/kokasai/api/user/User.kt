package com.kokasai.api.user

import com.kokasai.api.KokasaiApi
import com.kokasai.api.group.Group
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

data class User(val name: String) {
    lateinit var file: UserFile
        private set

    private val filePath = "user/$name.json"

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(UserFile::from) ?: UserFile()
    }

    suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }

    companion object {
        suspend fun isAdmin(name: String) = get(name).file.group.contains(Group.Name.admin)

        suspend fun get(name: String) = User(name).apply {
            load()
        }
    }
}
