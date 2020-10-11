import html.tagName.*
import kotlinx.browser.*
import kotlinx.dom.*
import org.w3c.dom.*
import org.w3c.dom.events.*

object HeaderJs: Js {
    val body
        get() = document.body
    val hamburger_icon
        get() = document.getElementById(Header.Id.hamburger_icon) as? HTMLElement
    val hamburger_menu
        get() = document.getElementById(Header.Id.hamburger_menu) as? HTMLElement
    val hamburger_menu_element_a
        get() = document.querySelectorAll("#${Header.Id.hamburger_menu} .${Header.Class.menu_element} a").asList()
    val hambruger_background
        get() = document.getElementById(Header.Id.hamburger_background) as? HTMLElement

    override val onLoad: ((Event) -> dynamic)? = {
        hamburger_icon?.addEventListener("click", {
            body?.classList?.toggle(Header.Class.active_hamburger)
        })
        hamburger_menu_element_a.forEach {
            if (it !is HTMLElement) return@forEach
            it.addEventListener("click", { event ->
                body?.removeClass(Header.Class.active_hamburger)
                event.preventDefault()
                val url = it.getAttribute("href")
                if (url.isNullOrEmpty()) return@addEventListener
                window.setTimeout({
                    window.location.href = url
                }, 250)
            })
        }
        hambruger_background?.addEventListener("click", {
            body?.removeClass(Header.Class.active_hamburger)
        })
    }
}