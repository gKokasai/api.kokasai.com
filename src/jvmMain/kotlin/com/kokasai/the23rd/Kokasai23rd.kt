package com.kokasai.the23rd

import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.database.SQLiteWithWebDAVDatabaseProvider
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.route.buildRoute
import com.kokasai.the23rd.configure.configureAuthCookie
import com.kokasai.the23rd.configure.configureFormAuth
import com.kokasai.the23rd.configure.configureGson
import com.kokasai.the23rd.configure.configureSessionAuth
import com.kokasai.the23rd.routes.cssRoutes
import com.kokasai.the23rd.routes.fileRoutes
import com.kokasai.the23rd.routes.html.HtmlRouteBuilder
import com.kokasai.the23rd.routes.staticRoute
import com.kokasai.the23rd.routes.testRoute
import com.kokasai.the23rd.routes.websocket.WebSocketRouteBuilder
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.basic
import io.ktor.features.ContentNegotiation
import io.ktor.sessions.Sessions
import io.ktor.websocket.WebSockets

object Kokasai23rd : FlowerKt() {
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(
        HttpClient(OkHttp) {
            install(Auth) {
                basic {
                    username = SystemEnv.WebDAV.UserName
                    password = SystemEnv.WebDAV.Password
                }
            }
        },
        SystemEnv.WebDAV.Url
    )
    override val databaseProvider = SQLiteWithWebDAVDatabaseProvider(".data.db", fileProvider, 5 * 60 * 1000)
    override val routeBuilder = HtmlRouteBuilder + WebSocketRouteBuilder + buildRoute {
        fileRoutes()
        cssRoutes()
        staticRoute()

        testRoute()
    }

    override fun Application.installKtorFeature() {
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
    }
}
