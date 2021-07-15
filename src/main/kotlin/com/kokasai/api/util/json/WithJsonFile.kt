package com.kokasai.api.util.json

import com.kokasai.api.KokasaiApi.Companion.api
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ConcurrentHashMap

abstract class WithJsonFile<T : JsonFile>(
    private val filePath: String,
    private val companion: JsonFile.Companion<T>
) {
    init {
        runBlocking {
            load()
        }
    }

    protected lateinit var file: T
        private set

    private suspend fun load() {
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

    abstract class Companion<K, V : WithJsonFile<*>> {
        private val cache = ConcurrentHashMap<K, V>()

        fun get(key: K): V = cache.getOrPut(key) { create(key) }

        abstract fun create(key: K): V
    }
}
