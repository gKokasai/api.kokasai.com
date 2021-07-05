package com.kokasai.api.http._dsl

import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

/**
 * Admin かグループに属するユーザーであれば処理を実行する
 * @param groupName グループ名
 * @param onSuccess 実行する処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.onlyAdminOrGroupUser(
    groupName: String,
    onSuccess: (
        user: User
    ) -> Unit
) {
    nowLogin { user ->
        if (user.file.group.contains(groupName) || user.isAdmin) {
            onSuccess(user)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
