package com.kokasai.api.form

import com.kokasai.api.util.json.WithJsonFile

data class FormDefine(
    val name: String
) : WithJsonFile<FormDefineFile>(
    "form/$name.json",
    FormDefineFile.Companion
) {
    companion object {
        suspend fun get(name: String) = FormDefine(name).apply {
            load()
        }
    }
}
