package com.gitlab.nitgc.kokasai.the23rd.constants

object HtmlRoutes {
    object Home: RoutePath("/")
    object Login: RoutePath("/login")
    object Logout: RoutePath("/logout")
    object Account: RoutePath("/account")
    object Access: RoutePath("/access") {
        object Bus: RoutePath(this, "/bus")
    }
    object Api: RoutePath("/api") {
        object Bus: RoutePath(this, "/bus") {
            object Challenge: RoutePath(this, "/challenge")
            object Route: RoutePath(this, "/route")
        }
    }
    sealed class Css(path: String): RoutePath(path) {
        object Header: Css("/header.css")
    }
    sealed class Js(path: String): RoutePath(path) {
        object Header: Js("/header.js")
        object BusUpdater: Js("/bus_updater.js")
    }
}

open class RoutePath(val path: String, val fullpath: String = path) {
    constructor(parent: RoutePath, path: String): this(path, parent.path + path)
}