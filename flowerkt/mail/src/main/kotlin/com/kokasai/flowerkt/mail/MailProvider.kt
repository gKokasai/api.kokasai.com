package com.kokasai.flowerkt.mail

interface MailProvider {
    fun sendMessage(toEmailAddress: String, subject: String, content: String): String
}
