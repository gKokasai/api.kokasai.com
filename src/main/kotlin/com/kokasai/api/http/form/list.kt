package com.kokasai.api.http.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.SimpleFormData
import com.kokasai.api.http._dsl.onlyAdminOrFormOwner
import com.kokasai.api.http._dsl.parameter
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: Map<String, SimpleFormData>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("formName") { formName ->
        onlyAdminOrFormOwner(formName) { _, _ ->
            val groupNames = FormDefine.get(formName).group
            val group = groupNames.associateWith {
                SimpleFormData.from(formName, it)
            }
            call.respond(GetListResponse(group))
        }
    }
}
