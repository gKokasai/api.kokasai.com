package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.WebSocketRoute
import io.ktor.routing.Route
import io.ktor.websocket.DefaultWebSocketServerSession
import io.ktor.websocket.webSocket

fun Route.webSocket(path: WebSocketRoute.Path, protocol: String? = null, handler: suspend DefaultWebSocketServerSession.() -> Unit) = webSocket(path.path, protocol, handler)