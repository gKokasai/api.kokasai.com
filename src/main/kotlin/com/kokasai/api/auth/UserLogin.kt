package com.kokasai.api.auth

import io.ktor.auth.Principal

object UserLogin {
    const val realm = "Kokasai User Login"
    const val authName = "User"
    const val sessionHeader = "Session"

    data class Data(val name: String) : Principal
}
