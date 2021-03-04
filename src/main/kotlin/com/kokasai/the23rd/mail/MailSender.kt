package com.kokasai.the23rd.mail

import com.kokasai.the23rd.routes.http.LoginRequest
import org.slf4j.LoggerFactory

object MailSender {
    private val loggerInsteadMail = LoggerFactory.getLogger("Mail")

    fun sendToken(loginRequest: LoginRequest, token: String) {
        val id = loginRequest.id
        val loginUrl = loginRequest.url + "?id=$id&token=$token"
        loggerInsteadMail.info("mail to: $id / login url: $loginUrl")
    }
}
