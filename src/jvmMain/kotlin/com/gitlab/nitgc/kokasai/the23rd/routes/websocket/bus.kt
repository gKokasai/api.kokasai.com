package com.gitlab.nitgc.kokasai.the23rd.routes.websocket

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import io.ktor.http.cio.websocket.*

val bus = webSocket {
    outgoing.send(Frame.Text("Bus"))
}
