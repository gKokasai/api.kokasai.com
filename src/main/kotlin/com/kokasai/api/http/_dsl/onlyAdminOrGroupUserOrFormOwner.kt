package com.kokasai.api.http._dsl

import com.kokasai.api.form.FormDefine
import com.kokasai.api.user.User
import com.kokasai.api.user.User.Companion.isAdmin
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

/**
 * Admin かグループに属するユーザーかフォームの担当であれば処理を実行する
 * @param groupName グループ名
 * @param formName フォーム名
 * @param onSuccess 実行する処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.onlyAdminOrGroupUserOrFormOwner(
    groupName: String,
    formName: String,
    onSuccess: (
        user: User,
        formDefine: FormDefine
    ) -> Unit
) {
    nowLogin { user ->
        val form = FormDefine.get(formName)
        if (user.group.contains(groupName) || form.owner.contains(user.name) || user.isAdmin) {
            onSuccess(user, form)
        } else {
            call.respond(HttpStatusCode.Forbidden)
        }
    }
}
