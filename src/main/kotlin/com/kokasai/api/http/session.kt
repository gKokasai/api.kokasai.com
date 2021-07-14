package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class GetResponse(val count: Long)

val sessionGet: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        call.respond(GetResponse(UserLogin.getSessionCount(user.name)))
    }
}
