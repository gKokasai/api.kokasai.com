package com.kokasai.api.http._dsl

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.user.User
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.util.pipeline.PipelineContext

/**
 * ログイン中のみ実行する処理
 * @param onSuccess 実行する処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.nowLogin(
    onSuccess: (
        user: User
    ) -> Unit
) {
    val principal = call.sessions.get<UserLogin.Data>()
    if (principal != null) {
        val userName = principal.name
        val user = User.get(userName)
        onSuccess(user)
    } else {
        call.respond(HttpStatusCode.Unauthorized)
    }
}
