import constants.*
import org.w3c.dom.*
import org.w3c.dom.events.*

object BusUpdaterJs: Js {
    override val onLoad: ((Event) -> dynamic) = {
        WebSocket("$WEB_SOCKET_HOST:$WEB_SOCKET_PORT/bus").apply {
            onopen = {
                console.info("[open] Connection established")
            }
            onmessage = { event ->
                console.info("[message] Data received from server: ${event.data}")
            }
            onclose = { event ->
                if (event is CloseEvent) {
                    if (event.wasClean) {
                        console.info("[close] Connection closed cleanly, code=${event.code} reason=${event.reason}")
                    } else {
                        // サーバのプロセスが停止 ネットワークダウン
                        console.info("[close] Connection died")
                    }
                }
            }
            onerror = { event ->
                if (event is ErrorEvent) {
                    console.info("[error] ${event.message}")
                }
            }
        }
    }
}