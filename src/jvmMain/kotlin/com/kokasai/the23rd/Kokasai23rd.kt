package com.kokasai.the23rd

import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.database.RemoteSQLiteDatabaseProvider
import com.kokasai.flowerkt.database.UseExposedDatabase
import com.kokasai.flowerkt.file.UseFile
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.route.buildRoute
import com.kokasai.flowerkt.session.SessionTable
import com.kokasai.flowerkt.session.UseSessionExposedDatabase
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
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.features.ContentNegotiation
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.ApplicationEngineFactory
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.websocket.WebSockets
import java.time.Duration

object Kokasai23rd : FlowerKt(), UseFile, UseSessionExposedDatabase, UseExposedDatabase {
    override val engine = Netty as ApplicationEngineFactory<ApplicationEngine, ApplicationEngine.Configuration>
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(OkHttp, SystemEnv.WebDAV.UserName, SystemEnv.WebDAV.Password, SystemEnv.WebDAV.Url)
    override val databaseProvider = RemoteSQLiteDatabaseProvider(".data.db", fileProvider, 5 * 60 * 1000)
    override val routeBuilder = HtmlRouteBuilder + WebSocketRouteBuilder + buildRoute {
        fileRoutes()
        cssRoutes()
        staticRoute()

        testRoute()
    }
    override val sessionTable = SessionTable("session", Duration.ofDays(30))

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

    override fun onLaunch() {
        super<UseSessionExposedDatabase>.onLaunch()
    }
}
