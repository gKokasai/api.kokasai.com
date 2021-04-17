package com.kokasai.api.routes.http

import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.api.routes.http.group.document.list as groupDocumentList
import com.kokasai.api.routes.http.user.document.list as userDocumentList

object HttpRoute : RoutePath("/") {
    override val child = setOf(Index, Auth, Login, Logout, File, Group, User)

    object Index : RoutePath("/", action = index)
    object Auth : RoutePath("/auth", action = auth)
    object Login : RoutePath("/login", action = login)
    object Logout : RoutePath("/login", action = logout)
    object File : RoutePath("/file", action = file)
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
