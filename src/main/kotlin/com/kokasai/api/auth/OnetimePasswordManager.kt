package com.kokasai.api.auth

import com.kokasai.api.KokasaiApi
import com.kokasai.api.mail.MailSender
import com.kokasai.api.routes.http.LoginRequest
import org.koin.java.KoinJavaComponent.inject
import java.util.Timer
import kotlin.concurrent.timerTask

object OnetimePasswordManager {
    private val api by inject<KokasaiApi>(KokasaiApi::class.java)

    data class Password(val pass: String = generatePassword()) {
        var failureCount = 0

        companion object {
            const val MaxFailureCount = 5
        }
    }

    private val passwords = mutableMapOf<String, Password>()

    private fun generatePassword(): String {
        val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(8) { chars.random() }.joinToString("")
    }

    fun generate(loginRequest: LoginRequest): Boolean {
        return if (passwords.contains(loginRequest.id)) {
            false
        } else {
            passwords[loginRequest.id] = Password().apply {
                MailSender.sendPass(loginRequest, pass)
            }
            Timer().schedule(
                timerTask {
                    passwords.remove(loginRequest.id)
                },
                5 * 60 * 1000
            )
            true
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
                api.logger.trace("Auth-TooManyRequest: $id")
                passwords.remove(id)
            }
            false
        }
    }
}
