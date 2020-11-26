package com.kokasai.the23rd.routes.html.access

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.html.*

object Access: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Access.Bus to bus
    )
}