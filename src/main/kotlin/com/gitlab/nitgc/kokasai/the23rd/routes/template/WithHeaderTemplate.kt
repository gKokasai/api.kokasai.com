package com.gitlab.nitgc.kokasai.the23rd.routes.template

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import io.ktor.utils.io.charsets.Charset
import kotlinx.css.*
import kotlinx.css.properties.*
import kotlinx.html.*

object WithHeaderTemplate : Template<HTML> {
    val body = Placeholder<DIV>()

    override fun HTML.apply() {
        lang = "ja"
        head {
            meta {
                charset = Charset.defaultCharset().toString()
            }
            meta("description", "第２３回群馬高専工華祭の公式ウェブサイトです。")
            meta("viewport", "width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no")
            title("工華祭")
            link(rel = LinkRel.stylesheet, href = "/header.css", type = StyleType.textCss)
            script(src = "https://code.jquery.com/jquery-3.5.1.min.js", type = ScriptType.textJavaScript) {}
            script {
                @Suppress("DEPRECATION")
                +"""
                    $(function(){
                        $('#hamburger_menu_icon').on('click', function() {
                            if($('html').hasClass('active_hamburger')){
                                $('#hamburger_menu_content').slideUp(200);
                            } else {
                                $('#hamburger_menu_content').slideDown(500);
                            }
                            $('html').toggleClass('active_hamburger');
                            return false;
                        });
                        $('#hamburger_menu_content .menu_element a').on('click', function(e){
                            $('html').removeClass('active_hamburger');
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
                        $('#hamburger_background').on('click', function(){
                            $('html').removeClass('active_hamburger');
                            $('#hamburger_menu_content').slideUp(200);
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
                    div {
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
                div {
                    id = "hamburger_menu_content"
                    ul {
                        menu("#", "ページ")
                        menu("#", "ページ")
                        menu("/account", "学内の方へ")
                    }
                }
                div {
                    id = "hamburger_background"
                }
            }
            div {
                id = "inner_body"
                insert(body)
            }
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
        "*" {
            margin(0.px)
            padding(0.px)
        }
        "body" {
            backgroundColor = BASE_COLOR
            fontFamily = "sans-serif"
        }
        "li" {
            listStyleType = ListStyleType.none
        }

        // ヘッダー
        "header" {
            backgroundColor = THEME_COLOR
            boxShadow(SHADOW_COLOR.withAlpha(0.4), offsetY = 1.6.px, blurRadius = 3.2.px)
        }
        "#inner_header" {
            display = Display.flex
            height = 40.px
            padding(vertical = 8.px)
        }

        // ヘッダータイトル
        "#header_title" {
            fontSize = 24.px
            margin(vertical = LinearDimension.auto)
            padding(top = 4.px, left = 24.px)
        }
        "#header_title a" {
            color = BASE_COLOR
            textDecoration = TextDecoration.none
        }

        // ハンバーガーメニュー
        "#horizontal_menu" {
            display = Display.none
        }
        "#hamburger_menu_icon" {
            position = Position.relative
            margin(LinearDimension.auto)
            marginRight = 24.px
            width = 25.px
            height = 22.px
            cursor = Cursor.pointer
        }
        "#hamburger_menu_icon span" {
            position = Position.absolute
            left = 0.px
            width = 100.pct
            height = 2.px
            backgroundColor = BASE_COLOR
            borderRadius = 2.px
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
            top = 10.px
        }
        "#hamburger_menu_icon span:nth-of-type(3)" {
            bottom = 0.px
        }
        "#hamburger_menu_content" {
            display = Display.none
        }
        ".active_hamburger #hamburger_menu_icon span:nth-of-type(1)" {
            transform {
                translateY(10.px)
                rotate(45.deg)
            }
        }
        ".active_hamburger #hamburger_menu_icon span:nth-of-type(2)" {
            opacity = 0
        }
        ".active_hamburger #hamburger_menu_icon span:nth-of-type(3)" {
            transform {
                translateY((-10).px)
                rotate((-45).deg)
            }
        }
        "#hamburger_background" {
            position = Position.fixed
            left = 0.px
            top = 0.px
            width = 100.pct
            height = 100.pct
            zIndex = -1
            backgroundColor = Color.black
            opacity = 0
            visibility = Visibility.hidden
            transition("all", .6.s)
            cursor = Cursor.pointer
        }
        ".active_hamburger #hamburger_background" {
            opacity = 0.6
            visibility = Visibility.visible
        }
        ".active_hamburger #hamburger_menu_content" {
            display = Display.block
        }
        "#hamburger_menu_content" {
            backgroundColor = THEME_COLOR
            width = 100.pct - 16.px
            marginLeft = 8.px
            padding(vertical = 8.px)
            borderTop(2.4.px, BorderStyle.dotted, BASE_COLOR)
        }
        "#hamburger_menu_content ul" {
            padding(8.px, 24.px)
        }
        "#hamburger_menu_content .menu_element+ .menu_element" {
            padding(vertical = 4.px)
        }
        "#hamburger_menu_content .menu_element a" {
            display = Display.block
            fontSize = 20.px
            fontWeight = FontWeight.w500
            color = BASE_COLOR
            textDecoration = TextDecoration.none
        }

        // インナーボディー
        "#inner_body" {
            width = 100.pct - 16.px
            margin(vertical = 8.px, horizontal = LinearDimension.auto)
        }

        media("screen and (min-width: 769px)") {
            // 横並びメニュー
            "#hamburger_menu_icon" {
                display = Display.none
            }
            "#horizontal_menu" {
                display = Display.block
                fontSize = 20.px
                margin(LinearDimension.auto)
                marginRight = 0.px
                paddingTop = 2.4.px
            }
            "#horizontal_menu .menu_element" {
                padding(horizontal = 24.px)
                display = Display.inline
            }
            "#horizontal_menu .menu_element+ .menu_element" {
                borderLeft(2.4.px, BorderStyle.solid, BASE_COLOR)
            }
            "#horizontal_menu .menu_element a" {
                fontWeight = FontWeight.w500
                color = BASE_COLOR
                textDecoration = TextDecoration.none
                position = Position.relative
            }
            "#horizontal_menu .menu_element a::after" {
                position = Position.absolute
                bottom = (-1.6).px
                left = 0.px
                content = "".quoted
                width = 100.pct
                borderTop(2.4.px, BorderStyle.solid, BASE_COLOR)
                transform {
                    scale(0, 1)
                }
                transition("transform", .2.s)
            }
            "#horizontal_menu .menu_element a:hover::after" {
                transform {
                    scale(1, 1)
                }
            }

            // インナーボディー
            "#inner_body" {
                width = 1000.px
            }
        }
    }
}