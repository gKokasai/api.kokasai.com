package com.gitlab.nitgc.kokasai.the23rd.constants

object HtmlRoute {
    object Home: Path("/")
    object Login: Path("/login")
    object Logout: Path("/logout")
    object Account: Path("/account")
    object Access: Path("/access") {
        object Bus: Path(this, "/bus")
    }
    object Api: Path("/api") {
        object Bus: Path(this, "/bus") {
            object Challenge: Path(this, "/challenge")
            object Route: Path(this, "/route")
        }
    }
    sealed class Css(path: String): Path(path) {
        object Header: Css("/header.css")
    }
    sealed class Js(path: String): Path(path) {
        object Header: Js("/header.js")
        object BusUpdater: Js("/bus_updater.js")
    }

    open class Path(val path: String, val fullpath: String = path) {
        constructor(parent: Path, path: String): this(path, parent.path + path)
    }
}

object WebSocketRoute {
    object Bus: Path("/bus")

    open class Path(val path: String, val fullpath: String = path) {
        constructor(parent: Path, path: String): this(path, parent.path + path)
    }
}

