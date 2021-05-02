package com.kokasai.api.user

import com.kokasai.api.group.Group
import com.kokasai.api.util.json.JsonFile
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class UserFile(
    val group: MutableList<String> = mutableListOf()
) : JsonFile() {
    suspend fun getGroup() = group.map { Group.get(it) }

    suspend fun getDocument() = getGroup().map { it.file.document }.flatten()

    companion object : JsonFile.Companion<UserFile> {
        override fun from(json: String): UserFile? = Json.decodeFromString(json)

        override fun empty() = UserFile()
    }
}
