package com.kokasai.api.form

import kotlinx.serialization.Serializable

@Serializable
data class FormSubmitFile(
    val values: Map<Int, FormSaveType>
)
