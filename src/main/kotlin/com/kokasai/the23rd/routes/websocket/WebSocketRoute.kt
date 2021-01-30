package com.kokasai.the23rd.routes.websocket

import com.kokasai.flowerkt.route.RoutePath

object WebSocketRoute : RoutePath("/") {
    override val child = setOf(Bus)

    object Bus : RoutePath("/bus")
}
