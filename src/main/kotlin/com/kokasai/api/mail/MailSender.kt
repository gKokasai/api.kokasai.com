package com.kokasai.api.mail

import com.kokasai.api.SystemEnv
import com.kokasai.api.routes.http.LoginRequest
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.SendGrid
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Content
import com.sendgrid.helpers.mail.objects.Email
import org.slf4j.LoggerFactory
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.TimeZone

object MailSender {
    private val sendGrid = SendGrid(SystemEnv.SendGrid.ApiKey)

    private val logger = LoggerFactory.getLogger("Mail")

    fun sendPass(loginRequest: LoginRequest, pass: String) {
        val from = Email("noreply@kokasai.com")
        val subject = "【重要】工華祭ウェブサイトのパスワード"
        val to = Email(loginRequest.id + "@gunma.kosen-ac.jp")
        val currentDate = SimpleDateFormat("yyyy/MM/dd HH:mm").apply {
            timeZone = TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo"))
        }.format(Date())
        val content = Content(
            "text/plain",
            """
                $currentDate にパスワードリクエストがありました。心当たりがない場合は無視してください。5回以上パスワードを間違えた場合、パスワード生成から5分経った場合は削除されます。その場合はもう一度パスワードリクエストを行ってください。
                
                パスワードは $pass です。
                
                何かあれば 群馬高専工華祭実行委員会 広報課 までお知らせください。
            """.trimIndent()
        )
        val mail = Mail(from, subject, to, content)
        val request = Request()
        try {
            request.method = Method.POST
            request.endpoint = "mail/send"
            request.body = mail.build()
            val response = sendGrid.api(request)
            logger.debug(
                """
                    To: ${to.email}
                    StatusCode: ${response.statusCode}
                    Body: ${response.body}
                    Header: ${response.headers}
                """.trimIndent()
            )
        } catch (ex: IOException) {
            throw ex
        }
    }
}
