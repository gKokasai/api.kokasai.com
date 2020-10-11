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

    override val onLoad: ((Event) -> dynamic)? = {
        hamburger_icon?.addEventListener("click", {
            body?.classList?.toggle(Header.Class.active_hamburger)
        })
    }
}