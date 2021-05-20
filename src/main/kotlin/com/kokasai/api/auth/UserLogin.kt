package com.kokasai.api.auth

import com.kokasai.api.KokasaiApi.Companion.api
import io.ktor.auth.Principal
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

object UserLogin {
    const val realm = "Kokasai User Login"
    const val authName = "User"
    const val sessionHeader = "Session"

    data class Data(val name: String) : Principal

    fun getSessionCount(name: String): Long {
        var count = 0L
        transaction(Connection.TRANSACTION_SERIALIZABLE, 3) {
            count = api.sessionTable.select {
                api.sessionTable.value eq "name=%23s$name"
            }.count()
        }
        return count
    }
}
