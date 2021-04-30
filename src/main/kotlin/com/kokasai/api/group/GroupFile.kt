package com.kokasai.api.group

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class GroupFile(
    var owner: List<String> = listOf(),
    var member: List<String> = listOf(),
    var document: List<String> = listOf(),
    var form: List<String> = listOf()
) {
    override fun toString(): String = Json.encodeToString(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@GroupFile.toString().toByteArray())
    }

    companion object {
        private fun from(json: String): GroupFile? = Json.decodeFromString(json)

        fun from(json: File) = from(json.readText())
    }
}
