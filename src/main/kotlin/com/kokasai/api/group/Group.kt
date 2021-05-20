package com.kokasai.api.group

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.util.Directory
import com.kokasai.api.util.json.WithJsonFile

data class Group(
    val name: String
) : WithJsonFile<GroupFile>(
    "${Directory.group}/$name.json",
    GroupFile.Companion
) {
    object Name {
        const val admin = "admin"
    }

    companion object {
        suspend fun get(name: String) = Group(name).apply {
            load()
        }

        suspend fun list() = api.fileProvider.list(Directory.group).orEmpty().map { it.substringBeforeLast('.') }
    }
}
