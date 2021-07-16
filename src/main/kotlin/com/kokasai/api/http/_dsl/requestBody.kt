package com.kokasai.api.http._dsl

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

/**
 * リクエストボディーを取得し、処理を実行する
 * @param T リクエストボディーの型
 * @param onSuccess 取得できた時の処理
 */
suspend inline fun <reified T : Any> PipelineContext<Unit, ApplicationCall>.requestBody(onSuccess: (T) -> Unit) {
    try {
        call.receive<T>().run(onSuccess)
    } catch (ex: ContentTransformationException) {
        call.respond(HttpStatusCode.BadRequest)
    }
}
