package com.kokasai.api.http

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.response.respondRedirect
import io.ktor.util.pipeline.PipelineInterceptor

val indexGet: PipelineInterceptor<Unit, ApplicationCall> = {
    call.respondRedirect("https://github.com/gKokasai/api.kokasai.com/blob/master/DOCUMENT.md")
}
