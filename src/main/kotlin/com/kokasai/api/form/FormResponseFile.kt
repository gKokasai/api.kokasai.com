package com.kokasai.api.form

import kotlinx.serialization.Serializable

@Serializable
data class FormResponseFile(
    val values: Map<Int, FormSaveType>,
    val comment: String,
    val status: Int
)
