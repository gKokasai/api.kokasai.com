package com.kokasai.api.group

import com.kokasai.api.util.json.JsonFile
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class GroupFile(
    var owner: List<String> = listOf(),
    var member: List<String> = listOf(),
    var document: List<String> = listOf(),
    val form: MutableList<String> = mutableListOf()
) : JsonFile() {
    override fun toJson() = Json.encodeToString(this)

    companion object : JsonFile.Companion<GroupFile> {
        override fun from(json: String): GroupFile? = Json.decodeFromString(json)

        override fun empty() = GroupFile()
    }
}
