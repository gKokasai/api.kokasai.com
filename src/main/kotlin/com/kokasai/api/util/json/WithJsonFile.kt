package com.kokasai.api.util.json

import com.kokasai.api.KokasaiApi.Companion.api

abstract class WithJsonFile<T : JsonFile>(
    private val filePath: String,
    private val companion: JsonFile.Companion<T>
) {
    lateinit var file: T
        private set

    suspend fun load() {
        file = api.fileProvider.get(filePath)?.let(companion::from) ?: companion.empty()
    }

    suspend fun save() {
        val file = file.toFile()
        api.fileProvider.add(filePath, file)
    }
}
