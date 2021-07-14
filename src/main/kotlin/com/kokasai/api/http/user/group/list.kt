package com.kokasai.api.http.user.group

import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: List<String>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        val group = user.file.group
        call.respond(GetListResponse(group))
    }
}
