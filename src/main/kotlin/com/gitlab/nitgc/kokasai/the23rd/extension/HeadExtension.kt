package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.HtmlRoutes
import kotlinx.html.*

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: HtmlRoutes.Css) = link(rel = LinkRel.stylesheet, href = href.fullpath, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.css(href: String) = link(rel = LinkRel.stylesheet, href = href, type = StyleType.textCss)

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: HtmlRoutes.Js) = script(src = src.fullpath, type = ScriptType.textJavaScript) {}

@HtmlTagMarker
fun FlowOrPhrasingOrMetaDataContent.javaScript(src: String) = script(src = src, type = ScriptType.textJavaScript) {}