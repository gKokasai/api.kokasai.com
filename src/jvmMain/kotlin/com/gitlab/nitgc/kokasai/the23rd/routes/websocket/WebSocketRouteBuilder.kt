package com.gitlab.nitgc.kokasai.the23rd.routes.websocket

import com.gitlab.nitgc.kokasai.flowerkt.route.*

object WebSocketRouteBuilder: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        WebSocketRoute.Bus to bus
    )
}
