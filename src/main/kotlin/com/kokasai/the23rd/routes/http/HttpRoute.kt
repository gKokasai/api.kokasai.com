package com.kokasai.the23rd.routes.http

import com.kokasai.flowerkt.route.RoutePath

object HttpRoute {
    object Login : RoutePath("/login")
    object Logout : RoutePath("/login")
    object Bus : RoutePath("/bus") {
        object Challenge : RoutePath(this, "/challenge")
        object Route : RoutePath(this, "/route")
    }
}
