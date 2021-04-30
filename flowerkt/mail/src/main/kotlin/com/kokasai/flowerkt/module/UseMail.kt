package com.kokasai.flowerkt.module

import com.kokasai.flowerkt.mail.MailProvider

interface UseMail : UseModule {
    /**
     * メールの送信先
     */
    val mailProvider: MailProvider
}
