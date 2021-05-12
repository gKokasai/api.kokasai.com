package com.kokasai.api.http._dsl

import com.kokasai.api.user.User
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend inline fun PipelineContext<Unit, ApplicationCall>.inGroup(
    groupName: String,
    onSuccess: (
        user: User
    ) -> Unit
) {
    onlyUser { user ->
        val groups = user.file.group
        if (groups.contains(groupName)) {
            onSuccess(user)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
