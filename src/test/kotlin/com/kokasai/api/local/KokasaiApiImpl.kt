package com.kokasai.api.local

import com.kokasai.api.KokasaiApi
import com.kokasai.api.configure.configureAuthCookie
import com.kokasai.api.configure.configureCORS
import com.kokasai.api.configure.configureCallLogging
import com.kokasai.api.configure.configureFormAuth
import com.kokasai.api.configure.configureGson
import com.kokasai.api.configure.configureSessionAuth
import com.kokasai.api.configure.configureStatusPages
import com.kokasai.api.http.HttpRoute
import com.kokasai.flowerkt.database.SQLiteDatabaseProvider
import com.kokasai.flowerkt.file.LocalFileProvider
import com.kokasai.flowerkt.mail.ConsoleMailProvider
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.AutoHeadResponse
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.server.netty.Netty
import io.ktor.sessions.Sessions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class KokasaiApiImpl : KokasaiApi {
    override val logger: Logger = LoggerFactory.getLogger("KokasaiAPI")
    override val engine = Netty
    override val port = 8081
    private val parentDirectory = File("test/local").apply(File::mkdirs)
    override val fileProvider = LocalFileProvider(parentDirectory)
    override val databaseProvider = SQLiteDatabaseProvider(parentDirectory.resolve("data.db").toRelativeString(File(".")))
    override val mailProvider = ConsoleMailProvider
    override val routePath = setOf(HttpRoute)

    override val sessionsConfiguration: Sessions.Configuration.() -> Unit = {
        configureAuthCookie()
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
                configureFormAuth()
                configureSessionAuth()
            }
            install(CORS) {
                configureCORS()
            }
            install(ContentNegotiation) {
                configureGson()
            }
        }
    }
}
