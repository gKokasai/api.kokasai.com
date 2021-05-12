package com.kokasai.api.http._dsl

import com.kokasai.api.user.User
import io.ktor.application.ApplicationCall
import io.ktor.util.pipeline.PipelineContext

suspend inline fun PipelineContext<Unit, ApplicationCall>.inGroupFromParameter(
    groupNameParameter: String,
    onSuccess: (
        user: User,
        groupName: String
    ) -> Unit
) {
    parameter(groupNameParameter) { groupName ->
        inGroup(groupName) { user ->
            onSuccess(user, groupName)
        }
    }
}
