package com.kokasai.api.http._dsl

import com.kokasai.api.group.Group
import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

/**
 * Admin かグループのオーナーであれば処理を実行する
 * @param groupName グループ名
 * @param onSuccess 実行する処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.onlyAdminOrGroupOwner(
    groupName: String,
    onSuccess: (
        user: User,
        group: Group
    ) -> Unit
) {
    nowLogin { user ->
        val group = Group.get(groupName)
        if (group.owner.contains(user.name) || user.isAdmin) {
            onSuccess(user, group)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
