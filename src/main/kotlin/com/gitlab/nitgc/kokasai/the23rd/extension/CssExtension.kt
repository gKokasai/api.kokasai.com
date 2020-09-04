package com.gitlab.nitgc.kokasai.the23rd.extension

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import kotlinx.css.*
import kotlinx.html.*

fun CommonAttributeGroupFacade.styleCss(builder: CSSBuilder.() -> Unit) {
    style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}