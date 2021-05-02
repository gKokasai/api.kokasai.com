package com.kokasai.api.group

import com.kokasai.api.util.json.WithJsonFile

data class Group(
    val name: String
) : WithJsonFile<GroupFile>(
    "group/$name.json",
    GroupFile.Companion
) {
    object Name {
        const val admin = "admin"
    }

    companion object {
        suspend fun get(name: String) = Group(name).apply {
            load()
        }
    }
}
