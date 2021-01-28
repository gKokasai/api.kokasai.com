package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.the23rd.routes.html.bus.Bus

object HtmlRouteBuilder : RouteBuilder.Container {
    override val routes = mapOf(
        HtmlRoute.Login to login,
        HtmlRoute.Logout to logout,
        HtmlRoute.Bus to Bus
    )
}
