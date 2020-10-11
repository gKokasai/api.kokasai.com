import kotlinx.browser.*
import org.w3c.dom.events.*

interface Js {
    val onLoad: ((Event) -> dynamic)?
        get() = null

    fun apply() {
        window.onload = onLoad
    }
}