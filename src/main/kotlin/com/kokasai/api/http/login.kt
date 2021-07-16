package com.kokasai.api.http

import com.kokasai.api.auth.OnetimePasswordManager
import com.kokasai.api.http._dsl.requestBody
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.ContentTransformationException
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.serialization.Serializable

@Serializable
data class PostLoginRequest(val id: String)

val loginPost: PipelineInterceptor<Unit, ApplicationCall> = {
    try {
        requestBody<PostLoginRequest> { (id) ->
            when {
                id.matches("^[A-Za-z][A-Za-z0-9.]*$".toRegex()).not() -> {
                    call.respond(HttpStatusCode.BadRequest)
                }
                OnetimePasswordManager.generate(id) -> {
                    call.respond(HttpStatusCode.OK)
                }
                else -> {
                    call.respond(HttpStatusCode.TooManyRequests)
                }
            }
        }
    } catch (ex: ContentTransformationException) {
        call.respond(HttpStatusCode.BadRequest)
    }
}
