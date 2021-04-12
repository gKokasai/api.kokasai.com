package com.kokasai.api.routes.websocket

import com.kokasai.flowerkt.route.RouteAction
import io.ktor.http.cio.websocket.Frame
import io.ktor.websocket.webSocket

val bus: RouteAction = {
    webSocket {
        outgoing.send(Frame.Text("Bus"))
    }
}
