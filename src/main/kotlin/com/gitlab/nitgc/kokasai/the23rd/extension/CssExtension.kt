package com.gitlab.nitgc.kokasai.the23rd.extension

import kotlinx.css.CSSBuilder
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.style

fun CommonAttributeGroupFacade.styleCss(builder: CSSBuilder.() -> Unit) {
    style = CSSBuilder().apply(builder).toString().trim()
}