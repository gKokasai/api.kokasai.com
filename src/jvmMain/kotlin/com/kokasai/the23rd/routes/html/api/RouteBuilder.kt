package com.kokasai.the23rd.routes.html.api

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.html.*
import com.kokasai.the23rd.routes.html.api.bus.*

object Api: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Api.Bus to Bus
    )
}