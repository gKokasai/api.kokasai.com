package com.gitlab.nitgc.kokasai.flowerkt.websocket

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import io.ktor.routing.*
import io.ktor.websocket.*

fun Route.webSocket(
    path: RoutePath,
    protocol: String? = null,
    handler: suspend DefaultWebSocketServerSession.() -> Unit
) = webSocket(path.path, protocol, handler)