package com.kokasai.flowerkt.module

import com.kokasai.flowerkt.session.SessionTable
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.sessions.Sessions
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.Duration

interface UseSessionExposedDatabase : UseExposedDatabase, LaunchProcess, InstallKtorProcess {
    /**
     * セッションを保存するデータベース
     */
    val sessionTable
        get() = SessionTable("session", Duration.ofDays(30))

    override fun launch() {
        databaseProvider.connect()
        transaction {
            create(sessionTable)
        }
    }

    val sessionsConfiguration: Sessions.Configuration.() -> Unit

    override fun installKtor(application: Application) {
        application.install(Sessions, sessionsConfiguration)
    }
}
