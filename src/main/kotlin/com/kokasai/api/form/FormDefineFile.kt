package com.kokasai.api.form

import com.kokasai.api.util.serialize.DateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class FormDefineFile(
    val name: String,
    val description: String,
    @Serializable(with = DateSerializer::class) val receive: Date,
    @Serializable(with = DateSerializer::class) val limit: Date,
    val values: Map<Int, FormDefineValue>,
    val group: List<String>
)

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
