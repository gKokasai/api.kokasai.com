package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.access.*

object HtmlRouteBuilder: RouteBuilder.Container {
    override val routes: Map<HtmlRoute.Path, RouteBuilder> = mapOf(
        HtmlRoute.Index to index,
        HtmlRoute.Access to Access
    )
}