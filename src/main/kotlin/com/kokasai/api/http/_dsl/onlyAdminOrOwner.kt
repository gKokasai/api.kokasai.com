package com.kokasai.api.http._dsl

import com.kokasai.api.group.Group
import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

suspend inline fun PipelineContext<Unit, ApplicationCall>.onlyAdminOrOwner(
    groupName: String,
    onSuccess: (
        user: User,
        group: Group
    ) -> Unit
) {
    onlyUser { user ->
        val group = Group.get(groupName)
        val owners = group.file.owner
        if (owners.contains(user.name) || user.isAdmin) {
            onSuccess(user, group)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
