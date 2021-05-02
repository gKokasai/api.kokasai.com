package com.kokasai.api.form

import com.kokasai.api.util.json.WithJsonFile

data class FormSave(
    val formName: String,
    val groupName: String
) : WithJsonFile<FormSaveFile>(
    "form/$formName/$groupName.json",
    FormSaveFile.Companion
) {
    companion object {
        suspend fun get(formName: String, groupName: String) = FormSave(formName, groupName).apply {
            load()
        }
    }
}
