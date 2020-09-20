package com.gitlab.nitgc.kokasai.the23rd.routes.html.api

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.api.bus.*

object Api: RouteBuilder.Container {
    override val routes = mapOf<HtmlRoute.Path, RouteBuilder>(
        HtmlRoute.Api.Bus to Bus
    )
}