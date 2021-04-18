package com.kokasai.api.user

import com.google.gson.Gson
import com.kokasai.api.group.Group
import java.io.File

data class UserFile(
    val group: MutableList<String>
) {
    suspend fun getGroup() = group.map { Group.get(it) }

    suspend fun getDocument() = getGroup().mapNotNull { it.file?.document }.flatten()

    override fun toString(): String = gson.toJson(this)

    fun toFile(): File = File.createTempFile("tmp", ".json").apply {
        writeBytes(this@UserFile.toString().toByteArray())
    }

    companion object {
        private val gson = Gson()

        private fun from(json: String): UserFile? = gson.fromJson(json, UserFile::class.java)

        fun from(json: File) = from(json.readText())
    }
}
