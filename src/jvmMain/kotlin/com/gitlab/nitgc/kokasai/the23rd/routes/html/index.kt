package com.gitlab.nitgc.kokasai.the23rd.routes.html

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*

val index = get {
    call.respondHtmlTemplate(WithHeaderTemplate(null)) {
        body {

        }
    }
}