package com.kokasai.api.form

import com.kokasai.api.util.json.JsonFile
import com.kokasai.api.util.serialize.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Date

@Serializable
data class FormDefineFile(
    val name: String = "",
    val description: String = "",
    @Serializable(with = DateSerializer::class) val receive: Date = Date(),
    @Serializable(with = DateSerializer::class) val limit: Date = Date(),
    val values: Map<Int, FormDefineValue> = mapOf(),
    var group: List<String> = listOf()
) : JsonFile() {
    override fun toJson() = Json.encodeToString(this)

    companion object : JsonFile.Companion<FormDefineFile> {
        override fun from(json: String): FormDefineFile? = Json.decodeFromString(json)

        override fun empty() = FormDefineFile()
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
    object String : FormDefineType()

    @Serializable
    @SerialName("check")
    data class Check(
        val element: Map<Int, kotlin.String>,
        val limit: Int
    ) : FormDefineType()
}
