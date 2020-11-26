package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.html.access.*
import com.kokasai.the23rd.routes.html.api.*

object HtmlRouteBuilder: RouteBuilder.Container {
    override val routes = mapOf(
        HtmlRoute.Index to index,
        HtmlRoute.Login to login,
        HtmlRoute.Logout to logout,
        HtmlRoute.Account to account,
        HtmlRoute.Access to Access,
        HtmlRoute.Api to Api
    )
}

