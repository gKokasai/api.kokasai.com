package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.http.cio.websocket.Frame
import io.ktor.routing.Routing
import io.ktor.websocket.webSocket

fun Routing.webSocketRoute() {
    webSocket("/bus") {
        outgoing.send(Frame.Text("Bus"))
    }
}