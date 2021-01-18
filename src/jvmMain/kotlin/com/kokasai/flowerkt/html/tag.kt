package com.kokasai.flowerkt.html

import com.kokasai.flowerkt.route.RoutePath
import com.kokasai.the23rd.routes.html.HtmlRoute
import kotlinx.html.A
import kotlinx.html.FlowOrInteractiveOrPhrasingContent
import kotlinx.html.FlowOrPhrasingOrMetaDataContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.LinkRel
import kotlinx.html.ScriptType
import kotlinx.html.StyleType
import kotlinx.html.a
import kotlinx.html.link
import kotlinx.html.script

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: HtmlRoute.Css) =
    link(rel = LinkRel.stylesheet, href = href.fullPath, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: HtmlRoute.Js) =
    script(src = src.fullPath, type = ScriptType.textJavaScript) {}

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(
    href: RoutePath? = null,
    target: String? = null,
    classes: String? = null,
    crossinline block: A.() -> Unit = {}
) = a(href?.fullPath, target, classes, block)
