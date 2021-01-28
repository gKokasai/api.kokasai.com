package com.kokasai.the23rd.routes.html.bus

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.the23rd.routes.html.HtmlRoute

object Bus : RouteBuilder.Container {
    override val routes = mapOf(
        HtmlRoute.Bus.Challenge to challenge,
        HtmlRoute.Bus.Route to route
    )
}
