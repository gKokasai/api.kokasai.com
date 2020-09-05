package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import kotlinx.html.*

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: HtmlRoute.Css) = link(rel = LinkRel.stylesheet, href = href.fullpath, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: HtmlRoute.Js) = script(src = src.fullpath, type = ScriptType.textJavaScript) {}
