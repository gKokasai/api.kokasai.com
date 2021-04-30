package com.kokasai.api.group

import com.kokasai.api.KokasaiApi
import org.koin.java.KoinJavaComponent.inject

private val api by inject<KokasaiApi>(KokasaiApi::class.java)

data class Group(val name: String) {
    lateinit var file: GroupFile
        private set

    private val filePath = "group/$name.json"

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(GroupFile::from) ?: GroupFile()
    }

    suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }

    object Name {
        const val admin = "admin"
    }

    companion object {
        suspend fun get(name: String) = Group(name).apply {
            load()
        }
    }
}
