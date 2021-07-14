package com.kokasai.api.http.user.document

import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetListResponse(val document: List<String>)

val listGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        val document = user.file.getDocument()
        call.respond(GetListResponse(document))
    }
}
