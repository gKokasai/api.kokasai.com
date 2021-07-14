package com.kokasai.api.http.group.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormDefineType
import com.kokasai.api.form.FormSave
import com.kokasai.api.form.FormSaveValue
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.util.serialize.DateSerializer
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class GetGetResponse(
    val name: String,
    val description: String,
    @Serializable(with = DateSerializer::class) val receive: Date,
    @Serializable(with = DateSerializer::class) val limit: Date,
    @Serializable(with = DateSerializer::class) val update: Date,
    val values: Map<Int, Value>,
    val comment: String,
    val status: Int
)

@Serializable
data class Value(
    val name: String,
    val description: String,
    val type: FormDefineType,
    val value: FormSaveValue?
)

val getGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName", "formName") { groupName, formName ->
        onlyAdminOrGroupUser(groupName) {
            val formDefine = FormDefine.get(formName)
            if (formDefine.group.contains(groupName)) {
                val formSave = FormSave.get(formName, groupName)
                val response = GetGetResponse(
                    formDefine.name,
                    formDefine.description,
                    formDefine.receive,
                    formDefine.limit,
                    formSave.update,
                    formDefine.values.mapValues { (index, defineValue) ->
                        Value(
                            defineValue.name,
                            defineValue.description,
                            defineValue.type,
                            formSave.values[index]
                        )
                    },
                    formSave.comment,
                    formSave.status
                )
                call.respond(response)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
