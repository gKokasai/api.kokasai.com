package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import kotlinx.html.*

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(href: HtmlRoute.Path? = null, target: String? = null, classes: String? = null, crossinline block: A.() -> Unit = {}) = a(href?.full_path, target, classes, block)

@ContextDsl
fun Route.route(path: HtmlRoute.Path, build: Route.() -> Unit): Route = route(path.path, build)

@ContextDsl
fun Route.get(path: HtmlRoute.Path, body: PipelineInterceptor<Unit, ApplicationCall>) = get(path.path, body)

suspend inline fun ApplicationCall.respondRedirect(url: HtmlRoute.Path, queryParameters: List<String> = listOf(), permanent: Boolean = false) = respondRedirect(url.full_path + queryParameters.joinToString("&", "?"), permanent)

suspend inline fun ApplicationCall.respondRedirect(url: Pair<HtmlRoute.Path, List<String>>, permanent: Boolean = false) = respondRedirect(url.first, url.second, permanent)