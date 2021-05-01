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
data class FormSaveFile(
    @Serializable(with = DateSerializer::class) var update: Date = Date(),
    var values: Map<Int, FormSaveValue> = mapOf(),
    val comment: String = "",
    var status: Int = 0
) {
    override fun toString(): String = Json.encodeToString(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@FormSaveFile.toString().toByteArray())
    }

    companion object {
        private fun from(json: String): FormSaveFile? = Json.decodeFromString(json)

        fun from(json: File) = from(json.readText())
    }
}

@Serializable
data class FormSaveValue(
    val value: FormSaveType,
    val comment: String
)

@Serializable
sealed class FormSaveType {
    @Serializable
    @SerialName("string")
    data class String(val content: kotlin.String)

    @Serializable
    @SerialName("check")
    data class Check(val select: List<Int>)
}
