package com.kokasai.the23rd.auth

import com.kokasai.the23rd.Kokasai23rd
import com.kokasai.the23rd.mail.MailSender
import com.kokasai.the23rd.routes.http.AuthRequest
import com.kokasai.the23rd.routes.http.LoginRequest

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

    enum class AuthResult {
        Success, TooManyRequest, Error
    }

    fun auth(authRequest: AuthRequest): AuthResult {
        val password = passwords[authRequest.id] ?: return AuthResult.Error
        return if (password.pass == authRequest.pass) {
            passwords.remove(authRequest.id)
            AuthResult.Success
        } else {
            password.failureCount ++
            if (Password.MaxFailureCount < password.failureCount) {
                Kokasai23rd.logger.trace("Auth-TooManyRequest: ${authRequest.id}")
                passwords.remove(authRequest.id)
                AuthResult.TooManyRequest
            } else {
                AuthResult.Error
            }
        }
    }
}
