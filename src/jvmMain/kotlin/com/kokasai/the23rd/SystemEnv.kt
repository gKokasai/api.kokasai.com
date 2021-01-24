package com.kokasai.the23rd

import com.kokasai.flowerkt.env.SystemEnvContainer

object SystemEnv : SystemEnvContainer {
    object Server {
        val Port by intOrNull("PORT")
    }

    object WebDAV {
        val UserName by string("WEB_DAV_USERNAME")
        val Password by string("WEB_DAV_PASSWORD")
        val Url by string("WEB_DAV_URL")
    }
}
