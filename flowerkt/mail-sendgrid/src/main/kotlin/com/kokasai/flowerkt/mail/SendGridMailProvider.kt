package com.kokasai.flowerkt.mail

import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import java.io.IOException

class SendGridMailProvider(apiKey: String, fromEmailAddress: String) : MailProvider {
    private val sendGrid = SendGrid(apiKey)
    private val fromEmail = Email(fromEmailAddress)

    override fun sendMessage(toEmailAddress: String, subject: String, content: String): String {
        val toEmail = Email(toEmailAddress)
        val mail = Mail(fromEmail, subject, toEmail, Content("text/plain", content))
        val request = Request().apply {
            method = Method.POST
            endpoint = "mail/send"
            body = mail.build()
        }
        return try {
            val response = sendGrid.api(request)
            """
                To: $toEmailAddress
                StatusCode: ${response.statusCode}
                Body: ${response.body}
                Header: ${response.headers}
            """.trimIndent()
        } catch (ex: IOException) {
            throw ex
        }
    }
}
