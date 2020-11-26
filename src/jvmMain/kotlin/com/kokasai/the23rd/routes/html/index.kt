package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*

val index = get {
    call.respondHtmlTemplate(WithHeaderTemplate(null)) {
        body {

        }
    }
}