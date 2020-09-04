package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.*
import kotlinx.html.*

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: HtmlRoute.Css) = link(rel = LinkRel.stylesheet, href = href.fullpath, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: String) = link(rel = LinkRel.stylesheet, href = href, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: HtmlRoute.Js) = script(src = src.fullpath, type = ScriptType.textJavaScript) {}

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: String) = script(src = src, type = ScriptType.textJavaScript) {}