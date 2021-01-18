package com.kokasai.the23rd.routes.html

import com.kokasai.flowerkt.route.get
import com.kokasai.the23rd.routes.template.WithHeaderTemplate
import io.ktor.application.call
import io.ktor.html.respondHtmlTemplate

val index = get {
    call.respondHtmlTemplate(WithHeaderTemplate(null)) {
        body {
        }
    }
}
