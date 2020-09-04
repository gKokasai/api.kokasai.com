package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.WebSocketRoute
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.http.cio.websocket.Frame
import io.ktor.routing.Routing

fun Routing.webSocketRoute() {
    webSocket(WebSocketRoute.Bus) {
        outgoing.send(Frame.Text("Bus"))
    }
}