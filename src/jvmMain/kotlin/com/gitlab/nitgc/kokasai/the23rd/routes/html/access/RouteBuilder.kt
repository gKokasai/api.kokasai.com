package com.gitlab.nitgc.kokasai.the23rd.routes.html.access

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*

object Access: RouteBuilder.Container {
    override val routes = mapOf<HtmlRoute.Path, RouteBuilder>(
        HtmlRoute.Access.Bus to bus
    )
}