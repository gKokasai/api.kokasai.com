package com.kokasai.api.routes.http

import com.kokasai.api.routes.http.bus.challenge
import com.kokasai.api.routes.http.bus.route
import com.kokasai.flowerkt.route.RoutePath

object HttpRoute : RoutePath("/") {
    override val child = setOf(Index, Auth, Login, Logout, File, Bus)

    object Index : RoutePath("/", action = index)
    object Auth : RoutePath("/auth", action = auth)
    object Login : RoutePath("/login", action = login)
    object Logout : RoutePath("/login", action = logout)
    object File : RoutePath("/file", action = file)
    object Bus : RoutePath("/bus") {
        override val child = setOf(Challenge, Route)

        object Challenge : RoutePath(this, "/challenge", action = challenge)
        object Route : RoutePath(this, "/route", action = route)
    }
}
