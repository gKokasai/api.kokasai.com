package com.kokasai.api.form

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.util.Directory
import com.kokasai.api.util.json.WithJsonFile

data class FormSave(
    val formName: String,
    val groupName: String
) : WithJsonFile<FormSaveFile>(
    "${Directory.form}/$formName/$groupName.json",
    FormSaveFile.Companion
) {
    override suspend fun save() {
        api.fileProvider.mkdir("${Directory.form}/$formName")
        super.save()
    }

    companion object {
        suspend fun get(formName: String, groupName: String) = FormSave(formName, groupName).apply {
            load()
        }
    }
}
