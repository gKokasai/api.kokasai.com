package com.kokasai.the23rd.mail

import com.kokasai.the23rd.routes.http.LoginRequest
import org.slf4j.LoggerFactory

object MailSender {
    private val loggerInsteadMail = LoggerFactory.getLogger("Mail")

    fun sendPass(loginRequest: LoginRequest, pass: String) {
        val id = loginRequest.id
        loggerInsteadMail.info("mail to: $id / pass: $pass")
    }
}
