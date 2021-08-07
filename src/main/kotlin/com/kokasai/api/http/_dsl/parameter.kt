package com.kokasai.api.http._dsl

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext

/**
 * パラメータが設定されていれば処理を実行する
 * @param name パラメータ名
 * @param onSuccess パラメータが設定されていた時の処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.parameters(
    name: String,
    onSuccess: (
        params: List<String>
    ) -> Unit
) {
    val params = call.parameters.getAll(name)
    if (params != null) {
        onSuccess(params)
    } else {
        call.respond(HttpStatusCode.BadRequest)
    }
}

/**
 * パラメータが設定されていれば処理を実行する
 * @param name パラメータ名
 * @param onSuccess パラメータが設定されていた時の処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.parameter(
    name: String,
    onSuccess: (
        param: String
    ) -> Unit
) {
    val param = call.parameters[name]
    if (param != null) {
        onSuccess(param)
    } else {
        call.respond(HttpStatusCode.BadRequest)
    }
}

/**
 * パラメータが設定されていれば処理を実行する
 * @param name1 パラメータ名
 * @param name2 パラメータ名
 * @param onSuccess パラメータが設定されていた時の処理
 */
suspend inline fun PipelineContext<Unit, ApplicationCall>.parameter(
    name1: String,
    name2: String,
    onSuccess: (
        param1: String,
        param2: String
    ) -> Unit
) {
    parameter(name1) { param1 ->
        parameter(name2) { param2 ->
            onSuccess(param1, param2)
        }
    }
}
