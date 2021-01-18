package com.kokasai.flowerkt.html

import kotlinx.html.FlowOrPhrasingOrMetaDataContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.LinkRel
import kotlinx.html.ScriptType
import kotlinx.html.StyleType
import kotlinx.html.link
import kotlinx.html.script

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: String) = link(rel = LinkRel.stylesheet, href = href, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: String) = script(src = src, type = ScriptType.textJavaScript) {}
