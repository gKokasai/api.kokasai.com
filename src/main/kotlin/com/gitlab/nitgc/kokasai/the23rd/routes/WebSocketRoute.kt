package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.http.cio.websocket.*
import io.ktor.routing.*

fun Routing.webSocketRoute() {
    webSocket(WebSocketRoute.Bus) {
        outgoing.send(Frame.Text("Bus"))
    }
}