package com.kokasai.the23rd.routes.html.api.bus

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.the23rd.routes.html.HtmlRoute

object Bus : RouteBuilder.Container {
    override val routes = mapOf(
        HtmlRoute.Api.Bus.Challenge to challenge,
        HtmlRoute.Api.Bus.Route to route
    )
}
