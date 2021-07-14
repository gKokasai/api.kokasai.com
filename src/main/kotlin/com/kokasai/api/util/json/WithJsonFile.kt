package com.kokasai.api.util.json

import com.kokasai.api.KokasaiApi.Companion.api

abstract class WithJsonFile<T : JsonFile>(
    private val filePath: String,
    private val companion: JsonFile.Companion<T>
) {
    protected var file: T = companion.empty()
        private set

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(companion::from) ?: companion.empty()
    }

    protected open suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }

    suspend fun edit(action: T.() -> Unit) {
        file.action()
        save()
    }
}
