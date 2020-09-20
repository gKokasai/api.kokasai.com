package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.access.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.api.*
import io.ktor.routing.*

object HtmlRouteBuilder: RouteBuilder.Container {
    override fun build(route: Route) {
        super.build(route)
    }

    override val routes = mapOf(
        HtmlRoute.Index to index,
        HtmlRoute.Login to login,
        HtmlRoute.Logout to logout,
        HtmlRoute.Account to account,
        HtmlRoute.Access to Access,
        HtmlRoute.Api to Api
    )
}

