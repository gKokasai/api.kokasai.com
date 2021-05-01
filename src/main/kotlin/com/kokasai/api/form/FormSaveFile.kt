package com.kokasai.api.form

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FormSaveFile(
    val values: Map<Int, FormSaveValue>,
    val comment: String,
    val status: Int
)

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
