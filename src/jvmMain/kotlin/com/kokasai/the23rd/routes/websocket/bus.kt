package com.kokasai.the23rd.routes.websocket

import com.kokasai.flowerkt.route.webSocket
import io.ktor.http.cio.websocket.Frame

val bus = webSocket {
    outgoing.send(Frame.Text("Bus"))
}
