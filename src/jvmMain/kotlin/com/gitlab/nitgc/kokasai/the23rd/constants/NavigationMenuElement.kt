package com.gitlab.nitgc.kokasai.the23rd.constants

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*

data class NavigationMenuElement(val href: String, val name: String) {
    constructor(route: RoutePath, name: String): this(route.full_path, name)

    companion object {
        val list = listOf(
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement(HtmlRoute.Account, "学内の方へ")
        )
    }
}