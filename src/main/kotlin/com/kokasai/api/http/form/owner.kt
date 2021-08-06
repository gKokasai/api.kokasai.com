package com.kokasai.api.http.form

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetOwnerResponse(val form: List<String>)

val ownerGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        val groupNames = user.group
        val ownerForm = groupNames.flatMap { groupName ->
            val group = Group.get(groupName)
            group.ownerForm
        }
        call.respond(GetOwnerResponse(ownerForm))
    }
}
