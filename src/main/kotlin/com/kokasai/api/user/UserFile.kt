package com.kokasai.api.user

import com.kokasai.api.util.json.JsonFile
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class UserFile(
    var group: List<String> = listOf()
) : JsonFile() {
    override fun toJson() = Json.encodeToString(this)

    companion object : JsonFile.Companion<UserFile> {
        override fun from(json: String): UserFile? = Json.decodeFromString(json)

        override fun empty() = UserFile()
    }
}
