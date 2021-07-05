package com.kokasai.api.http._dsl

import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend inline fun PipelineContext<Unit, ApplicationCall>.onlyAdmin(
    onSuccess: (
        user: User
    ) -> Unit
) {
    nowLogin { user ->
        if (user.isAdmin) {
            onSuccess(user)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
