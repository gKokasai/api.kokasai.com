package com.kokasai.the23rd

import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.database.RemoteSQLiteDatabaseProvider
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.module.UseExposedDatabaseSQLite
import com.kokasai.flowerkt.module.UseFileWebDav
import com.kokasai.flowerkt.module.UseSessionExposedDatabase
import com.kokasai.the23rd.configure.configureAuthCookie
import com.kokasai.the23rd.configure.configureFormAuth
import com.kokasai.the23rd.configure.configureGson
import com.kokasai.the23rd.configure.configureSessionAuth
import com.kokasai.the23rd.routes.http.HttpRoute
import com.kokasai.the23rd.routes.websocket.WebSocketRoute
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import io.ktor.websocket.WebSockets

object Kokasai23rd : FlowerKt, UseFileWebDav, UseSessionExposedDatabase, UseExposedDatabaseSQLite {
    override val engine = Netty
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(OkHttp, SystemEnv.WebDAV.UserName, SystemEnv.WebDAV.Password, SystemEnv.WebDAV.Url)
    override val databaseProvider = RemoteSQLiteDatabaseProvider(".data.db", fileProvider, 5 * 60 * 1000)

    override val routePath = setOf(HttpRoute, WebSocketRoute)

    override val sessionsConfiguration: Sessions.Configuration.() -> Unit = {
        configureAuthCookie()
    }

    override fun installKtor(application: Application) {
        super<UseSessionExposedDatabase>.installKtor(application)
        application.run {
            install(Authentication) {
                configureFormAuth()
                configureSessionAuth()
            }
            install(CORS) {
                anyHost()
            }
            install(ContentNegotiation) {
                configureGson()
            }
            install(WebSockets)
        }
    }

    override fun launch() {
        super<UseSessionExposedDatabase>.launch()
        super<FlowerKt>.launch()
    }
}
