package com.gitlab.nitgc.kokasai.the23rd.extension

import kotlinx.html.*

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: String) = link(rel = LinkRel.stylesheet, href = href, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: String) = script(src = src, type = ScriptType.textJavaScript) {}