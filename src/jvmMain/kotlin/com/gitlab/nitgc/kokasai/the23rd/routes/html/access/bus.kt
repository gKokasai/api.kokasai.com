package com.gitlab.nitgc.kokasai.the23rd.routes.html.access

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*

val bus = get {
    call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス")) {

    }
}