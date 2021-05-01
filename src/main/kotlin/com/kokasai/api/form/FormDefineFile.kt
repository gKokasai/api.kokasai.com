package com.kokasai.api.form

import com.kokasai.api.util.serialize.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.Date

@Serializable
data class FormDefineFile(
    val name: String = "",
    val description: String = "",
    @Serializable(with = DateSerializer::class) val receive: Date = Date(),
    @Serializable(with = DateSerializer::class) val limit: Date = Date(),
    val values: Map<Int, FormDefineValue> = mapOf(),
    val group: List<String> = listOf()
) {
    override fun toString(): String = Json.encodeToString(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@FormDefineFile.toString().toByteArray())
    }

    companion object {
        private fun from(json: String): FormDefineFile? = Json.decodeFromString(json)

        fun from(json: File) = from(json.readText())
    }
}

@Serializable
data class FormDefineValue(
    val name: String,
    val description: String,
    val type: FormDefineType
)

@Serializable
sealed class FormDefineType {
    @Serializable
    @SerialName("string")
    object String : FormDefineType() {
        const val name = "string"
    }

    @Serializable
    @SerialName("check")
    data class Check(
        val element: Map<Int, kotlin.String>,
        val limit: Int
    ) : FormDefineType() {
        companion object {
            const val name = "check"
        }
    }
}
