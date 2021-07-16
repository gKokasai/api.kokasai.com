package com.kokasai.api.http.group.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormSave
import com.kokasai.api.form.SimpleFormData
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdminOrGroupUser
import com.kokasai.api.http._dsl.parameter
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val form: Map<String, SimpleFormData>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("groupName") { groupName ->
        onlyAdminOrGroupUser(groupName) {
            val group = Group.get(groupName)
            val formNames = group.form
            val form = formNames.associateWith { formName ->
                val formDefine = FormDefine.get(formName)
                val formSave = FormSave.get(formName, groupName)
                SimpleFormData.from(formDefine, formSave)
            }
            call.respond(GetListResponse(form))
        }
    }
}
