package com.kokasai.the23rd.routes.html.access

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*

val bus = get {
    call.respondHtmlTemplate(WithHeaderTemplate("アクセス バス")) {

    }
}