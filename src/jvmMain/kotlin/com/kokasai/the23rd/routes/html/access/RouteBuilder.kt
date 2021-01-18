package com.kokasai.the23rd.routes.html.access

import com.kokasai.flowerkt.route.RouteBuilder
import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.the23rd.routes.html.HtmlRoute

object Access : RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Access.Bus to bus
    )
}
