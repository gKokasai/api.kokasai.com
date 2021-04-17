package com.kokasai.api.routes.http

import com.kokasai.api.routes.http.bus.challenge
import com.kokasai.api.routes.http.bus.route
import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.api.routes.http.group.document.list as groupDocumentList
import com.kokasai.api.routes.http.user.document.list as userDocumentList

object HttpRoute : RoutePath("/") {
    override val child = setOf(Index, Auth, Login, Logout, File, Bus, Group, User)

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
    object Group : RoutePath("/group") {
        override val child = setOf(Document)

        object Document : RoutePath(this, "/document") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = groupDocumentList)
        }
    }
    object User : RoutePath("/user") {
        override val child = setOf(Document)

        object Document : RoutePath(this, "/document") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = userDocumentList)
        }
    }
}
