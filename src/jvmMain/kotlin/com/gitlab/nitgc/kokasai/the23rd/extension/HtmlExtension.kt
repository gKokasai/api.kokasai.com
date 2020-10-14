package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import io.ktor.application.*
import io.ktor.response.*
import kotlinx.html.*

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(
    href: RoutePath? = null,
    target: String? = null,
    classes: String? = null,
    crossinline block: A.() -> Unit = {}
) = a(href?.full_path, target, classes, block)

suspend inline fun ApplicationCall.respondRedirect(
    url: RoutePath,
    queryParameters: List<String> = listOf(),
    permanent: Boolean = false
) {
    if (queryParameters.isEmpty()) {
        respondRedirect(url.full_path, permanent)
    } else {
        respondRedirect(url.full_path + queryParameters.joinToString("&", "?"), permanent)
    }
}

suspend inline fun ApplicationCall.respondRedirect(
    url: Pair<RoutePath, List<String>>,
    permanent: Boolean = false
) = respondRedirect(url.first, url.second, permanent)