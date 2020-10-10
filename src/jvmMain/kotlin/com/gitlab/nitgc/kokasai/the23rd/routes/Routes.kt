package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.Companion.headerCss
import kotlinx.css.*

object HtmlRoute {
    object Index: Path("/")
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

    sealed class Css(path: String, val response: RuleSet?): Path(path) {
        object Header: Css("/header.css", headerCss)
    }

    sealed class Js(path: String): Path(path) {
        object JQuery: Js("https://code.jquery.com/jquery-3.5.1.min.js")
        object MainBundle: Js("/main.bundle.js")
        object Header: Js("/header.js")
        object BusUpdater: Js("/bus_updater.js")
    }

    open class Path(val path: String, val full_path: String = path) {
        constructor(parent: Path, path: String): this(path, parent.path + path)
    }
}

object WebSocketRoute {
    object Bus: Path("/bus")

    open class Path(val path: String, val full_path: String = path) {
        constructor(parent: Path, path: String): this(path, parent.path + path)
    }
}

