package com.kokasai.api.http

import com.kokasai.api.auth.UserLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.auth.principal
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineInterceptor

val authGet: PipelineInterceptor<Unit, ApplicationCall> = {
    val principal = call.sessions.get<UserLogin.Data>()
    if (principal != null) {
        call.respond(HttpStatusCode.OK)
    } else {
        call.respond(HttpStatusCode.Unauthorized)
    }
}

val authPost: PipelineInterceptor<Unit, ApplicationCall> = {
    val principal = call.principal<UserLogin.Data>()
    call.sessions.set(UserLogin.sessionHeader, principal)
    call.respond(HttpStatusCode.OK)
}
