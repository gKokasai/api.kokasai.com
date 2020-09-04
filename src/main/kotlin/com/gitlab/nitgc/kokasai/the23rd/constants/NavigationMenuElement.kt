package com.gitlab.nitgc.kokasai.the23rd.constants

data class NavigationMenuElement(val href: String, val name: String) {
    companion object {
        val list = listOf(
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement("#", "ページ"),
            NavigationMenuElement(Routes.ACCOUNT, "学内の方へ")
        )
    }
}