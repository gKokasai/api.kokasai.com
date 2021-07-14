package com.kokasai.api.http.user.form

import com.kokasai.api.http._dsl.nowLogin
import com.kokasai.api.http.group.form.SimpleGroupFormData
import com.kokasai.api.http.group.form.getGroupFormListResponse
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: Map<String, Map<String, SimpleGroupFormData>>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        call.respond(GetListResponse(user.group.associateWith { getGroupFormListResponse(it).form }))
    }
}
