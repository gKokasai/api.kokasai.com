package com.kokasai.api.auth

import com.kokasai.api.KokasaiAPI
import com.kokasai.api.mail.MailSender
import com.kokasai.api.routes.http.LoginRequest

object OnetimePasswordManager {
    data class Password(val pass: String = generatePassword()) {
        var failureCount = 0

        companion object {
            const val MaxFailureCount = 5
        }
    }

    private val passwords = mutableMapOf<String, Password>()

    private fun generatePassword(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(32) { chars.random() }.joinToString("")
    }

    fun generate(loginRequest: LoginRequest) {
        passwords.getOrPut(loginRequest.id) {
            Password().apply {
                MailSender.sendPass(loginRequest, pass)
            }
        }
    }

    fun auth(id: String, pass: String): Boolean {
        val password = passwords[id] ?: return false
        return if (password.pass == pass) {
            passwords.remove(id)
            true
        } else {
            password.failureCount ++
            if (Password.MaxFailureCount < password.failureCount) {
                KokasaiAPI.logger.trace("Auth-TooManyRequest: $id")
                passwords.remove(id)
            }
            false
        }
    }
}
