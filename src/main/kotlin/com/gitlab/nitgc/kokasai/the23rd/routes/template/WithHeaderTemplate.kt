package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import io.ktor.utils.io.charsets.Charset
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

object WithHeaderTemplate : Template<HTML> {
    val body = Placeholder<BODY>()

    override fun HTML.apply() {
        lang = "ja"
        head {
            meta {
                charset = Charset.defaultCharset().toString()
            }
            meta("description", "第２３回群馬高専工華祭の公式ウェブサイトです。")
            title("工華祭")
            link(rel = LinkRel.stylesheet, href = "/header.css", type = StyleType.textCss)
            script(src = "https://code.jquery.com/jquery-3.5.1.min.js", type = ScriptType.textJavaScript) {}
            script {
                @Suppress("DEPRECATION")
                +"""
                    $(function(){
                        $('#hamburger_menu_icon').on('click', function() {
                            if($('#hamburger_menu_content').hasClass('active')){
                                $('#hamburger_menu_content').slideUp(200);
                            } else {
                                $('#hamburger_menu_content').slideDown(500);
                            }
                            $('.hamburger_menu').toggleClass('active');
                            return false;
                        });
                        $('#hamburger_menu_content .menu_element a').on('click', function(e){
                            $('.hamburger_menu').removeClass('active');
                            $('#hamburger_menu_content').slideUp(200);
                            e.preventDefault();
                            url = $(this).attr('href');
                            if(url != ''){
                                setTimeout(function(){
                                    window.location = url;
                                }, 250);
                            }
                            return false;
                        });
                    });
                """.trimIndent()
            }
        }
        body {
            header {
                div {
                    id = "inner_header"
                    h1 {
                        id = "header_title"
                        a(href = "/") {
                            +"工華祭"
                        }
                    }
                    // ハンバーガーメニューアイコン
                    div("hamburger_menu") {
                        id = "hamburger_menu_icon"
                        span()
                        span()
                        span()
                    }
                    // 横並びメニュー
                    ul {
                        id = "horizontal_menu"
                        onTouchStart = ""
                        menu("#", "ページ")
                        menu("#", "ページ")
                        menu("/account", "学内の方へ")
                    }
                }
                // ハンバーガーメニュー
                div("hamburger_menu") {
                    id = "hamburger_menu_content"
                    ul {
                        menu("#", "ページ")
                        menu("#", "ページ")
                        menu("/account", "学内の方へ")
                    }
                }
            }
            insert(body)
        }
    }

    private fun UL.menu(href: String, name: String) {
        li("menu_element") {
            a(href = href) {
                +name
            }
        }
    }

    val headerCss: CSSBuilder.() -> Unit = {
        val useHamburgerAspectRatio = "27/50"

        "body" {
            backgroundColor = BASE_COLOR
            margin(0.vh)
        }
        "li" {
            listStyleType = ListStyleType.none
        }
        "header" {
            backgroundColor = THEME_COLOR
            boxShadow(SHADOW_COLOR.withAlpha(0.4), offsetY = 0.16.vh, blurRadius = 0.32.vh)
        }
        "#inner_header" {
            display = Display.flex
            padding(vertical = max(0.8.vh, 8.px))
        }
        "#header_title" {
            fontSize = max(3.2.vh, 32.px)
            margin(vertical = LinearDimension.auto)
            padding(top = 0.32.vh, left = 2.24.vh)
        }
        "#header_title a" {
            color = BASE_COLOR
            textDecoration = TextDecoration.none
        }
        // ハンバーガーメニューを使用
        media("(max-aspect-ratio: $useHamburgerAspectRatio)") {
            "#horizontal_menu" {
                display = Display.none
            }
            "#hamburger_menu_icon" {
                position = Position.relative
                margin(LinearDimension.auto)
                marginRight = 2.24.vh
                width = 50.px
                height = 44.px
                cursor = Cursor.pointer
            }
            "#hamburger_menu_icon span" {
                position = Position.absolute
                left = 0.px
                width = 100.pct
                height = 4.px
                backgroundColor = BASE_COLOR
                borderRadius = 4.px
            }
            "#hamburger_menu_icon, #hamburger_menu_icon span" {
                display = Display.inlineBlock
                transition("all", 0.5.s)
                boxSizing = BoxSizing.borderBox
            }
            "#hamburger_menu_icon span:nth-of-type(1)" {
                top = 0.px
            }
            "#hamburger_menu_icon span:nth-of-type(2)" {
                top = 20.px
            }
            "#hamburger_menu_icon span:nth-of-type(3)" {
                bottom = 0.px
            }
            "#hamburger_menu_content" {
                display = Display.none
            }
            "#hamburger_menu_icon.active span:nth-of-type(1)" {
                transform {
                    translateY(20.px)
                    rotate(45.deg)
                }
            }
            "#hamburger_menu_icon.active span:nth-of-type(2)" {
                opacity = 0
            }
            "#hamburger_menu_icon.active span:nth-of-type(3)" {
                transform {
                    translateY((-20).px)
                    rotate((-45).deg)
                }
            }
            "#hamburger_menu_content.active" {
                display = Display.block
            }
            "#hamburger_menu_content" {
                backgroundColor = THEME_COLOR
                width = 100.pct - 1.12.vh
                marginLeft = 0.56.vh
                padding(vertical = max(0.8.vh, 8.px))
                borderTop(0.24.vh, BorderStyle.dotted, BASE_COLOR)
            }
            "#hamburger_menu_content ul" {
                padding(horizontal = 2.24.vh)
            }
            ".menu_element+ .menu_element" {
                padding(vertical = max(0.4.vh, 4.px))
            }
            ".menu_element a" {
                display = Display.block
                fontSize = max(2.24.vh, 24.px)
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
            }
        }
        // 横並びメニューを使用
        media("(min-aspect-ratio: $useHamburgerAspectRatio)") {
            ".hamburger_menu" {
                display = Display.none
            }
            "#horizontal_menu" {
                fontSize = max(2.24.vh, 24.px)
                margin(LinearDimension.auto)
                marginRight = 0.vh
                paddingTop = 0.224.vh
            }
            ".menu_element" {
                padding(0.vh, 2.24.vh)
                display = Display.inline
            }
            ".menu_element+ .menu_element" {
                borderLeft(0.24.vh, BorderStyle.solid, BASE_COLOR)
            }
            ".menu_element a" {
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
                position = Position.relative
            }
            ".menu_element a::after" {
                position = Position.absolute
                bottom = (-0.16).vh
                left = 0.vh
                content = "".quoted
                width = 100.pct
                borderTop(0.24.vh, BorderStyle.solid, BASE_COLOR)
                transform {
                    scale(0, 1)
                }
                transition("transform", .2.s)
            }
            ".menu_element a:hover::after" {
                transform {
                    scale(1, 1)
                }
            }
        }
    }
}