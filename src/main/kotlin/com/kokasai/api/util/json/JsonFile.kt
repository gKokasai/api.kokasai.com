package com.kokasai.api.util.json

import java.io.File

abstract class JsonFile {
    abstract fun toJson(): String

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(toJson().toByteArray())
    }

    interface Companion<T : JsonFile> {
        fun from(json: String): T?

        fun from(json: File) = from(json.readText())

        fun empty(): T
    }
}
