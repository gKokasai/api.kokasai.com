package com.kokasai.api.form

import com.kokasai.api.KokasaiApi.Companion.api

data class FormDefine(val name: String) {
    lateinit var file: FormDefineFile
        private set

    private val filePath = "form/$name.json"

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(FormDefineFile::from) ?: FormDefineFile()
    }

    suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }

    companion object {
        suspend fun get(name: String) = FormDefine(name).apply {
            load()
        }
    }
}
