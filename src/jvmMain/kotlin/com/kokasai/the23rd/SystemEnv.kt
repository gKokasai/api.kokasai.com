package com.kokasai.the23rd

import com.kokasai.flowerkt.env.SystemEnvContainer

object SystemEnv : SystemEnvContainer {
    object Server {
        val Port by intOrNull("PORT")
    }

    object WebDEV {
        val UserName by string("WEB_DEV_USERNAME")
        val Password by string("WEB_DEV_PASSWORD")
        val Url by string("WEB_DEV_URL")
    }
}
