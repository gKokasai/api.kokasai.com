package com.kokasai.the23rd.constants

import io.ktor.auth.Principal

object Auth {
    object UserLogin {
        const val authName = "user-auth"
        const val realm = "Kokasai User Login"
        const val cookie = "auth"
        const val sessionName = "user-session"

        data class Data(val name: String) : Principal

        object Test {
            const val Username = "TestUser"
            const val Password = "test"
        }
    }
}

