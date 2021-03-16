package com.kokasai.the23rd.auth

import io.ktor.auth.Principal

object UserLogin {
    const val authName = "user-auth"
    const val realm = "Kokasai User Login"
    const val cookie = "auth"
    const val sessionName = "user-session"

    data class Data(val name: String) : Principal
}
