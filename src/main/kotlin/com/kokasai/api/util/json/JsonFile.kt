package com.kokasai.api.util.json

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

abstract class JsonFile {
    override fun toString(): String = Json.encodeToString(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@JsonFile.toString().toByteArray())
    }

    interface Companion<T : JsonFile> {
        fun from(json: String): T?

        fun from(json: File) = from(json.readText())

        fun empty(): T
    }
}
