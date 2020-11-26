package com.kokasai.the23rd.routes

import com.kokasai.the23rd.routes.template.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.testRoute() {
    get("/test") {
        call.respondHtmlTemplate(WithHeaderTemplate("テスト")) {
            body {
                h2 {
                    +"h2 h2 h2 h2"
                }
                h3 {
                    +"h3 h3 h3 h3"
                }
                h4 {
                    +"h4 h4 h4 h4"
                }
                h5 {
                    +"h5 h5 h5 h5"
                }
                h6 {
                    +"h6 h6 h6 h6"
                }
                p {
                    +"p p p p"
                }
            }
        }
    }
}