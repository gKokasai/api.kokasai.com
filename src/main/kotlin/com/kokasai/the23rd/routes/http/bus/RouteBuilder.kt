package com.kokasai.the23rd.routes.http.bus

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.the23rd.routes.http.HttpRoute

object Bus : RouteBuilder.Container {
    override val routes = mapOf(
        HttpRoute.Bus.Challenge to challenge,
        HttpRoute.Bus.Route to route
    )
}
