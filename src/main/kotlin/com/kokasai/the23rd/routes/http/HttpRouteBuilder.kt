package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.the23rd.routes.http.bus.Bus

object HttpRouteBuilder : RouteBuilder.Container {
    override val routes = mapOf(
        HttpRoute.Login to login,
        HttpRoute.Logout to logout,
        HttpRoute.Bus to Bus
    )
}
