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
            val group = FormDefine.get(formName).file.group
            call.respond(GetAssignResponse(group))
        }
    }
}

val assignPost: PipelineInterceptor<Unit, ApplicationCall> = {
    parameter("formName") { formName ->
        onlyAdminOrFormOwner(formName) { _, _ ->
            val request = call.receive<PostAssignRequest>()
            val formDefine = FormDefine.get(formName)
            val formDefineFile = formDefine.file
            val lastGroup = formDefineFile.group
            val group = request.group
            group.forEach {
                if (lastGroup.contains(it).not()) {
                    Group.get(it).apply {
                        file.form.add(formName)
                    }.save()
                }
            }
            lastGroup.forEach {
                if (group.contains(it).not()) {
                    Group.get(it).apply {
                        file.form.remove(formName)
                    }.save()
                }
            }
            formDefineFile.group = group
            formDefine.save()
            call.respond(HttpStatusCode.OK)
        }
    }
}
