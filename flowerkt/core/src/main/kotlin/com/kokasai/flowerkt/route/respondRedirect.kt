package com.kokasai.flowerkt.route

import io.ktor.application.ApplicationCall
import io.ktor.response.respondRedirect

suspend inline fun ApplicationCall.respondRedirect(
    url: RoutePath,
    queryParameters: List<String> = listOf(),
    permanent: Boolean = false
) {
    if (queryParameters.isEmpty()) {
        respondRedirect(url.fullPath, permanent)
    } else {
        respondRedirect(url.fullPath + queryParameters.joinToString("&", "?"), permanent)
    }
}

suspend inline fun ApplicationCall.respondRedirect(
    url: Pair<RoutePath, List<String>>,
    permanent: Boolean = false
) = respondRedirect(url.first, url.second, permanent)
