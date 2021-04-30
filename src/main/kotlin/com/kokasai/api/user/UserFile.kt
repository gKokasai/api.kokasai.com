package com.kokasai.api.user

import com.kokasai.api.group.Group
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class UserFile(
    val group: MutableList<String> = mutableListOf()
) {
    suspend fun getGroup() = group.map { Group.get(it) }

    suspend fun getDocument() = getGroup().map { it.file.document }.flatten()

    override fun toString(): String = Json.encodeToString(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@UserFile.toString().toByteArray())
    }

    companion object {
        private fun from(json: String): UserFile? = Json.decodeFromString(json)

        fun from(json: File) = from(json.readText())
    }
}
