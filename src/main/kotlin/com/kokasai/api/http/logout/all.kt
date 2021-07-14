package com.kokasai.api.http.logout

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.http._dsl.nowLogin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineInterceptor

val allPost: PipelineInterceptor<Unit, ApplicationCall> = {
    nowLogin { user ->
        UserLogin.logoutAll(user.name)
        call.sessions.clear(UserLogin.sessionHeader)
        call.respond(HttpStatusCode.OK)
    }
}
