package com.gitlab.nitgc.kokasai.the23rd.routes.html.access

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*

val bus = get {
    call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス", javaScripts = listOf(HtmlRoute.Js.BusUpdater))) {

    }
}