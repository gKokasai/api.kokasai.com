package com.kokasai.the23rd

import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.database.RemoteSQLiteDatabaseProvider
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.module.UseAuth
import com.kokasai.flowerkt.module.UseExposedDatabaseSQLite
import com.kokasai.flowerkt.module.UseFileWebDav
import com.kokasai.flowerkt.module.UseSessionExposedDatabase
import com.kokasai.flowerkt.module.UseWebsocket
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
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.features.ContentNegotiation
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.websocket.WebSockets

object Kokasai23rd : FlowerKt, UseAuth, UseFileWebDav, UseSessionExposedDatabase, UseExposedDatabaseSQLite, UseWebsocket {
    override val engine = Netty
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(OkHttp, SystemEnv.WebDAV.UserName, SystemEnv.WebDAV.Password, SystemEnv.WebDAV.Url)
    override val databaseProvider = RemoteSQLiteDatabaseProvider(".data.db", fileProvider, 5 * 60 * 1000)

    override val routeBuilder = HtmlRouteBuilder + WebSocketRouteBuilder + buildRoute {
        fileRoutes()
        cssRoutes()
        staticRoute()

        testRoute()
    }

    override val authenticationConfiguration: Authentication.Configuration.() -> Unit = {
        configureFormAuth()
        configureSessionAuth()
    }

    override val sessionsConfiguration: Sessions.Configuration.() -> Unit = {
        configureAuthCookie()
    }

    override val webSocketsOptions: WebSockets.WebSocketOptions.() -> Unit = {
    }

    override fun installKtor(application: Application) {
        super<UseAuth>.installKtor(application)
        super<UseSessionExposedDatabase>.installKtor(application)
        super<UseWebsocket>.installKtor(application)
        application.run {
            install(ContentNegotiation) {
                configureGson()
            }
        }
    }

    override fun launch() {
        super<UseSessionExposedDatabase>.launch()
        super<FlowerKt>.launch()
    }
}
