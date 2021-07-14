package com.kokasai.api.http.logout

import com.kokasai.api.auth.UserLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineInterceptor

val indexPost: PipelineInterceptor<Unit, ApplicationCall> = {
    call.sessions.clear(UserLogin.sessionHeader)
    call.respond(HttpStatusCode.OK)
}
