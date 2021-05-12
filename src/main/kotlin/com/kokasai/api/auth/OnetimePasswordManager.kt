package com.kokasai.api.auth

import com.kokasai.api.KokasaiApi.Companion.api
import org.slf4j.LoggerFactory
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.TimeZone
import java.util.Timer
import kotlin.concurrent.timerTask

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
        return List(8) { chars.random() }.joinToString("")
    }

    fun generate(id: String): Boolean {
        return if (passwords.contains(id)) {
            false
        } else {
            passwords[id] = Password().apply {
                sendPassword(id, pass)
            }
            Timer().schedule(
                timerTask {
                    passwords.remove(id)
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

    private val mailLogger = LoggerFactory.getLogger("Mail")

    private fun sendPassword(id: String, password: String) {
        val currentDate = SimpleDateFormat("yyyy/MM/dd HH:mm").apply {
            timeZone = TimeZone.getTimeZone(ZoneId.of("Asia/Tokyo"))
        }.format(Date())
        val content = """
            $currentDate にパスワードリクエストがありました。心当たりがない場合は無視してください。5回以上パスワードを間違えた場合、パスワード生成から5分経った場合は削除されます。その場合はもう一度パスワードリクエストを行ってください。
            
            パスワードは $password です。
            
            何かあれば 群馬高専工華祭実行委員会 広報課 までお知らせください。
        """.trimIndent()
        val response = api.mailProvider.sendMessage("$id@gunma.kosen-ac.jp", "【重要】工華祭ウェブサイトのパスワード", content)
        mailLogger.debug(response)
    }
}
