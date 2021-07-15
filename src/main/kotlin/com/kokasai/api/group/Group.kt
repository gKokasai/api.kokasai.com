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
    val owner by file::owner
    val member by file::member
    val document by file::document
    val form by file::form

    val allUser
        get() = owner + member

    object Name {
        const val admin = "admin"
    }

    companion object : WithJsonFile.Companion<String, Group>() {
        override fun create(key: String) = Group(key)

        suspend fun list() = api.fileProvider.list(Directory.group).orEmpty().map { it.substringBeforeLast('.') }
    }
}
