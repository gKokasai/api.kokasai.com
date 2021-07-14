package com.kokasai.api

import com.kokasai.api.auth.configureBasicAuth
import com.kokasai.api.auth.configureSessionAuth
import com.kokasai.api.auth.configureSessionHeader
import com.kokasai.api.http.httpRoute
import com.kokasai.flowerkt.database.RemoteSQLiteDatabaseProvider
import com.kokasai.flowerkt.file.WebDAVFileProvider
import com.kokasai.flowerkt.mail.SendGridMailProvider
import com.kokasai.flowerkt.module.UseFileWebDav
import com.kokasai.flowerkt.module.UseMailSendGrid
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.client.engine.cio.CIO
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.routing.routing
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class KokasaiApiImpl : KokasaiApi, UseFileWebDav, UseMailSendGrid {
    override val logger: Logger = LoggerFactory.getLogger("KokasaiAPI")
    override val engine = Netty
    override val port = SystemEnv.Server.Port ?: 8080
    override val fileProvider = WebDAVFileProvider(CIO, SystemEnv.WebDAV.UserName, SystemEnv.WebDAV.Password, SystemEnv.WebDAV.Url)
    override val databaseProvider = RemoteSQLiteDatabaseProvider(SystemEnv.Server.DatabaseFileName ?: "data.db", fileProvider, 60 * 1000)
    override val mailProvider = SendGridMailProvider(SystemEnv.SendGrid.ApiKey, "noreply@kokasai.com")

    override val sessionsConfiguration: Sessions.Configuration.() -> Unit = {
        configureSessionHeader()
    }

    override fun installKtor(application: Application) {
        super.installKtor(application)
        application.run {
            install(AutoHeadResponse)
            install(CallLogging) {
                configureCallLogging()
            }
            install(StatusPages) {
                configureStatusPages()
            }
            install(Authentication) {
                configureBasicAuth()
                configureSessionAuth()
            }
            install(CORS) {
                configureCORS()
            }
            install(ContentNegotiation) {
                configureSerialization()
            }
            routing {
                httpRoute()
            }
        }
    }
}
