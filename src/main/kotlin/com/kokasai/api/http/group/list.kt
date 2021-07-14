package com.kokasai.api.http.group

import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.onlyAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val group: List<String>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    onlyAdmin {
        call.respond(GetListResponse(Group.list()))
    }
}
