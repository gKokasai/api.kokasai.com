package com.gitlab.nitgc.kokasai.the23rd.routes.html.api.bus

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*

object Bus: RouteBuilder.Container {
    override val routes = mapOf(
        HtmlRoute.Api.Bus.Challenge to challenge,
        HtmlRoute.Api.Bus.Route to route
    )
}