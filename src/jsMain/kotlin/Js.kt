import kotlinx.browser.window
import org.w3c.dom.events.Event

interface Js {
    val onLoad: ((Event) -> dynamic)?
        get() = null

    fun apply() {
        window.onload = onLoad
    }
}
