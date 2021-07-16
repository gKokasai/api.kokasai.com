package com.kokasai.api.http.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdminOrFormOwner
import com.kokasai.api.http._dsl.parameter
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetAssignResponse(val group: List<String>)

@Serializable
data class PostAssignRequest(val group: List<String>)

val assignGet: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("formName") { formName ->
        onlyAdminOrFormOwner(formName) { _, _ ->
            val group = FormDefine.get(formName).group
            call.respond(GetAssignResponse(group))
        }
    }
}

val assignPost: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("formName") { formName ->
        onlyAdminOrFormOwner(formName) { _, _ ->
            val request = call.receive<PostAssignRequest>()
            val formDefine = FormDefine.get(formName)
            val lastGroup = formDefine.group
            val group = request.group
            group.forEach {
                if (lastGroup.contains(it).not()) {
                    Group.get(it).edit {
                        form += formName
                    }
                }
            }
            lastGroup.forEach {
                if (group.contains(it).not()) {
                    Group.get(it).edit {
                        form -= formName
                    }
                }
            }
            formDefine.edit {
                this.group = group
            }
            call.respond(HttpStatusCode.OK)
        }
    }
}
