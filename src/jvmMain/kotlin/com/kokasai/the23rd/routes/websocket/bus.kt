package com.kokasai.the23rd.routes.websocket

import com.kokasai.flowerkt.route.*
import io.ktor.http.cio.websocket.*

val bus = webSocket {
    outgoing.send(Frame.Text("Bus"))
}
