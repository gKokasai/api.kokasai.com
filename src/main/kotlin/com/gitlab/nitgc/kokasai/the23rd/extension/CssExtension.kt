package com.gitlab.nitgc.kokasai.the23rd.extension

import io.ktor.application.ApplicationCall
import io.ktor.http.ContentType
import io.ktor.response.respondText
import kotlinx.css.*
import kotlinx.html.*

fun CommonAttributeGroupFacade.styleCss(builder: CSSBuilder.() -> Unit) {
    style = CSSBuilder().apply(builder).toString().trim()
}

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}