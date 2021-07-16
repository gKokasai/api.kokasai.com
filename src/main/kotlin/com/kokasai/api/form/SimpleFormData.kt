package com.kokasai.api.form

import com.kokasai.api.util.serialize.DateSerializer
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class SimpleFormData(
    val name: String,
    @Serializable(with = DateSerializer::class) var update: Date,
    val status: Int
) {
    companion object {
        fun from(formDefine: FormDefine, formSave: FormSave): SimpleFormData {
            return SimpleFormData(
                formDefine.name,
                formSave.update,
                formSave.status
            )
        }

        fun from(formName: String, groupName: String): SimpleFormData {
            val formDefine = FormDefine.get(formName)
            val formSave = FormSave.get(formName, groupName)
            return from(formDefine, formSave)
        }
    }
}
