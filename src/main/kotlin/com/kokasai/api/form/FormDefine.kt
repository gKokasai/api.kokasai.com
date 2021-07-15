package com.kokasai.api.form

import com.kokasai.api.util.Directory
import com.kokasai.api.util.json.WithJsonFile

data class FormDefine(
    val fileName: String
) : WithJsonFile<FormDefineFile>(
    "${Directory.form}/$fileName.json",
    FormDefineFile.Companion
) {
    val name by file::name
    val description by file::description
    val receive by file::receive
    val limit by file::limit
    val values by file::values
    val group by file::group
    val owner by file::owner

    companion object : WithJsonFile.Companion<String, FormDefine>() {
        override fun create(key: String) = FormDefine(key)
    }
}
