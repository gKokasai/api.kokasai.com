package com.kokasai.api.http

import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.api.http.form.submit as formSubmit
import com.kokasai.api.http.group.document.list as groupDocumentList
import com.kokasai.api.http.group.user.list as groupUserList
import com.kokasai.api.http.user.document.list as userDocumentList
import com.kokasai.api.http.user.group.list as userGroupList

object HttpRoute : RoutePath("/") {
    override val child = setOf(Index, Auth, Login, Logout, File, Document, Form, Group, User)

    object Index : RoutePath("/", action = index)
    object Auth : RoutePath("/auth", action = auth)
    object Login : RoutePath("/login", action = login)
    object Logout : RoutePath("/logout", action = logout)
    object File : RoutePath("/file", action = file)
    object Document : RoutePath("/document", action = document)
    object Form : RoutePath("/form") {
        override val child = setOf(Submit)

        object Submit : RoutePath(this, "/submit", action = formSubmit)
    }
    object Group : RoutePath("/group") {
        override val child = setOf(Document, User)

        object Document : RoutePath(this, "/document") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = groupDocumentList)
        }
        object User : RoutePath(this, "/user") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = groupUserList)
        }
    }
    object User : RoutePath("/user") {
        override val child = setOf(Document, Group)

        object Document : RoutePath(this, "/document") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = userDocumentList)
        }
        object Group : RoutePath(this, "/group") {
            override val child = setOf(List)

            object List : RoutePath(this, "/list", action = userGroupList)
        }
    }
}
