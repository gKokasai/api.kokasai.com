package com.kokasai.api.form

import com.kokasai.api.KokasaiApi.Companion.api

data class FormSave(val formName: String, val groupName: String) {
    lateinit var file: FormSaveFile
        private set

    private val filePath = "form/$formName/$groupName.json"

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(FormSaveFile::from) ?: FormSaveFile()
    }

    suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }

    companion object {
        suspend fun get(formName: String, groupName: String) = FormSave(formName, groupName).apply {
            load()
        }
    }
}
