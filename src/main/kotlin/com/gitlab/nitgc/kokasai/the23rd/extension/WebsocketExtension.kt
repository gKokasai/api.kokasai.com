package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.routes.*
import io.ktor.routing.*
import io.ktor.websocket.*

fun Route.webSocket(
    path: WebSocketRoute.Path,
    protocol: String? = null,
    handler: suspend DefaultWebSocketServerSession.() -> Unit
) = webSocket(path.path, protocol, handler)