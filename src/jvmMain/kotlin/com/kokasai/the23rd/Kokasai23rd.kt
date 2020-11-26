package com.kokasai.the23rd

import com.kokasai.flowerkt.route.*
import com.kokasai.the23rd.routes.*
import com.kokasai.the23rd.routes.html.*
import com.kokasai.the23rd.routes.websocket.*

object Kokasai23rd: com.kokasai.flowerkt.FlowerKt {
    override val databaseUrl = "jdbc:sqlite:.data.db"
    override val port = 8080
    override val routeBuilder = HtmlRouteBuilder + WebSocketRouteBuilder + route {
        cssRoutes()
        staticRoute()

        testRoute()
    }
}