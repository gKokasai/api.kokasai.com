package com.kokasai.the23rd.routes.websocket

import com.kokasai.flowerkt.route.buildWebSocketRoute
import io.ktor.http.cio.websocket.Frame

val bus = buildWebSocketRoute {
    outgoing.send(Frame.Text("Bus"))
}
