package com.gitlab.nitgc.kokasai.the23rd.constants

import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.Companion.headerCss
import kotlinx.css.*

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
    sealed class Css(path: String, val response: (CSSBuilder.() -> Unit)?): Path(path) {
        object Header: Css("/header.css", headerCss)
    }
    sealed class Js(path: String): Path(path) {
        object JQuery: Js("https://code.jquery.com/jquery-3.5.1.min.js")
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

