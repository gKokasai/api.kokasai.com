package com.kokasai.api.http.user.form

import com.kokasai.api.form.SimpleFormData
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: Map<String, Map<String, SimpleFormData>>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        val groupNames = user.group
        val group = groupNames.associateWith { groupName ->
            val group = Group.get(groupName)
            group.form.associateWith { formName ->
                SimpleFormData.from(formName, groupName)
            }
        }
        call.respond(GetListResponse(group))
    }
}
