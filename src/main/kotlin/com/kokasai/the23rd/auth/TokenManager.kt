package com.kokasai.the23rd.auth

import com.kokasai.the23rd.Kokasai23rd
import com.kokasai.the23rd.mail.MailSender
import com.kokasai.the23rd.routes.http.AuthRequest
import com.kokasai.the23rd.routes.http.LoginRequest

object TokenManager {
    data class Token(val login: String = generateToken(), val auth: String = generateToken()) {
        var failureCount = 0

        companion object {
            const val MaxFailureCount = 5
        }
    }

    private val loginTokens = mutableMapOf<String, Token>()

    private fun generateToken(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(32) { chars.random() }.joinToString("")
    }

    fun generateLoginToken(loginRequest: LoginRequest): String {
        return loginTokens.getOrPut(loginRequest.id) {
            Token().apply {
                MailSender.sendToken(loginRequest, auth)
            }
        }.login
    }

    enum class AuthTokenResult {
        Success, TooManyRequest, Error
    }

    fun authToken(authRequest: AuthRequest): AuthTokenResult {
        val token = loginTokens[authRequest.id] ?: return AuthTokenResult.Error
        return if (token.auth == authRequest.token) {
            loginTokens.remove(authRequest.id)
            AuthTokenResult.Success
        } else {
            token.failureCount ++
            if (Token.MaxFailureCount < token.failureCount) {
                Kokasai23rd.logger.trace("Auth-TooManyRequest: ${authRequest.id}")
                loginTokens.remove(authRequest.id)
                AuthTokenResult.TooManyRequest
            } else {
                AuthTokenResult.Error
            }
        }
    }
}
