package com.kokasai.api.user

import com.google.gson.Gson
import java.io.File

data class UserFile(
    val group: List<String>
) {
    override fun toString(): String = gson.toJson(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(toString().toByteArray())
    }

    companion object {
        private val gson = Gson()

        private fun from(json: String): UserFile? = gson.fromJson(json, UserFile::class.java)

        fun from(json: File) = from(json.readText())
    }
}
