package com.kokasai.api.group

import com.google.gson.Gson
import java.io.File

data class GroupFile(
    val owner: List<String>,
    var document: List<String>
) {
    override fun toString(): String = gson.toJson(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(toString().toByteArray())
    }

    companion object {
        private val gson = Gson()

        private fun from(json: String): GroupFile? = gson.fromJson(json, GroupFile::class.java)

        fun from(json: File) = from(json.readText())
    }
}
