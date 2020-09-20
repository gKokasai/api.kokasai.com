package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.the23rd.configure.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.sessions.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.*

fun main(args: Array<String>) {
    Database.connect("jdbc:sqlite:.data.db")
    transaction {
        create(sessionTable)
    }
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
fun Application.launch() {
    install(Sessions) {
        configureAuthCookie()
    }

    install(Authentication) {
        configureFormAuth()
        configureSessionAuth()
    }

    install(ContentNegotiation) {
        configureGson()
    }

    install(WebSockets)

    routing {
        HtmlRouteBuilder.build(this)

        cssRoutes()
        staticRoute()
        webSocketRoute()

        testRoute()
    }
}
