package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(href: HtmlRoute.Path? = null, target: String? = null, classes: String? = null, crossinline block: A.() -> Unit = {}) = a(href?.fullpath, target, classes, block)

@ContextDsl
fun Route.route(path: HtmlRoute.Path, build: Route.() -> Unit): Route = route(path.path, build)

@ContextDsl
fun Route.get(path: HtmlRoute.Path, body: PipelineInterceptor<Unit, ApplicationCall>) = get(path.path, body)

suspend inline fun ApplicationCall.respondRedirect(url: HtmlRoute.Path, permanent: Boolean = false) = respondRedirect(url.fullpath, permanent)