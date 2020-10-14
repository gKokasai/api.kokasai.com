package com.gitlab.nitgc.kokasai.the23rd.routes.html.api

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.api.bus.*

object Api: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Api.Bus to Bus
    )
}