package com.kokasai.flowerkt.mail

object ConsoleMailProvider : MailProvider {
    override fun sendMessage(toEmailAddress: String, subject: String, content: String): String {
        return """
            to: $toEmailAddress
            subject: $subject
            content: $content
        """.trimIndent()
    }
}
