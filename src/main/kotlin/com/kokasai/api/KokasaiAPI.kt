package com.kokasai.api

import com.kokasai.api.configure.configureAuthCookie
import com.kokasai.api.configure.configureFormAuth
import com.kokasai.api.configure.configureGson
import com.kokasai.api.configure.configureSessionAuth
import com.kokasai.api.routes.http.HttpRoute
import com.kokasai.flowerkt.FlowerKt
import com.kokasai.flowerkt.database.RemoteSQLiteDatabaseProvider
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.module.UseExposedDatabaseSQLite
import com.kokasai.flowerkt.module.UseFileWebDav
import com.kokasai.flowerkt.module.UseSessionExposedDatabase
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.features.CORS
import io.ktor.features.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object KokasaiAPI : FlowerKt, UseFileWebDav, UseSessionExposedDatabase, UseExposedDatabaseSQLite {
    val logger: Logger = LoggerFactory.getLogger("KokasaiAPI")

    override val engine = Netty
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(OkHttp, SystemEnv.WebDAV.UserName, SystemEnv.WebDAV.Password, SystemEnv.WebDAV.Url)
    override val databaseProvider = RemoteSQLiteDatabaseProvider(SystemEnv.Server.DatabaseFileName ?: "data.db", fileProvider, 60 * 1000)

    override val routePath = setOf(HttpRoute)

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
                header(HttpHeaders.Authorization)
                host("kokasai.com", schemes = listOf("http", "https"))
            }
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
