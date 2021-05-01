package com.kokasai.api.form

import com.kokasai.api.util.serialize.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class FormGetFile(
    val name: String,
    val description: String,
    @Serializable(with = DateSerializer::class) val receive: Date,
    @Serializable(with = DateSerializer::class) val limit: Date,
    val values: Map<Int, FormGetValue>,
    val comment: String,
    val status: Int
)

@Serializable
data class FormGetValue(
    val name: String,
    val description: String,
    val type: FormDefineType,
    val value: FormSaveValue,
    val comment: String
)
