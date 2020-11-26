package com.kokasai.the23rd.routes.websocket

import com.kokasai.flowerkt.route.*

object WebSocketRouteBuilder: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        WebSocketRoute.Bus to bus
    )
}
