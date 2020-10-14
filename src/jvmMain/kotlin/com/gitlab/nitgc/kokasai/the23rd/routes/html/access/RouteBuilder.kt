package com.gitlab.nitgc.kokasai.the23rd.routes.html.access

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*

object Access: RouteBuilder.Container {
    override val routes = mapOf<RoutePath, RouteBuilder>(
        HtmlRoute.Access.Bus to bus
    )
}