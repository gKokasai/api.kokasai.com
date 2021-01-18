package com.kokasai.the23rd.extension.html

import com.kokasai.flowerkt.html.css
import com.kokasai.flowerkt.html.javaScript
import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.the23rd.routes.html.HtmlRoute
import kotlinx.html.A
import kotlinx.html.FlowOrInteractiveOrPhrasingContent
import kotlinx.html.FlowOrPhrasingOrMetaDataContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.a

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: HtmlRoute.Css) = css(href.fullPath)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: HtmlRoute.Js) = javaScript(src.fullPath)

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(href: RoutePath, crossinline block: A.() -> Unit = {}) = a(href.fullPath, block = block)
