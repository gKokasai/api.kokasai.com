package com.kokasai.api.http.group.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormSave
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import com.kokasai.api.util.serialize.DateSerializer
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class GetListResponse(val form: Map<String, SimpleGroupFormData>)

@Serializable
data class SimpleGroupFormData(
    val name: String,
    @Serializable(with = DateSerializer::class) var update: Date,
    val status: Int
)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdminOrGroupUser(groupName) {
            call.respond(getGroupFormListResponse(groupName))
        }
    }
}

suspend fun getGroupFormListResponse(groupName: String) = Group.get(groupName).run {
    GetListResponse(
        form.associateWith { formName ->
            val formDefine = FormDefine.get(formName)
            val formSave = FormSave.get(formName, groupName)
            SimpleGroupFormData(
                formDefine.name,
                formSave.update,
                formSave.status
            )
        }
    )
}
