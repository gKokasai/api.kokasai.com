package com.kokasai.the23rd.routes.html.api

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.the23rd.routes.html.HtmlRoute
import com.kokasai.the23rd.routes.html.api.bus.Bus

object Api : RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Api.Bus to Bus
    )
}
