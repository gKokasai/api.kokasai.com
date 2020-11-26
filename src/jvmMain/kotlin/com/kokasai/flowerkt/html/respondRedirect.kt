package com.kokasai.flowerkt.html

import com.kokasai.flowerkt.route.*
import io.ktor.application.*
import io.ktor.response.*

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