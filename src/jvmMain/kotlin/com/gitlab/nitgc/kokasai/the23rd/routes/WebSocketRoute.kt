package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.flowerkt.websocket.*
import com.gitlab.nitgc.kokasai.the23rd.routes.websocket.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*

fun Routing.webSocketRoute() {
    webSocket(WebSocketRoute.Bus) {
        outgoing.send(Frame.Text("Bus"))
    }
}