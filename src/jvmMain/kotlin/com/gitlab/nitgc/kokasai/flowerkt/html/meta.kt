package com.gitlab.nitgc.kokasai.flowerkt.html

import kotlinx.html.*

inline fun HEAD.meta(run: Meta.() -> Unit) = run.invoke(Meta(this))

class Meta(private val head: HEAD) {
    var charset: String
        get() = throw UnsupportedOperationException()
        set(value) = head.meta(charset = value)

    var description: String
        get() = throw UnsupportedOperationException()
        set(value) = head.meta("description", value)

    var viewport: String
        get() = throw UnsupportedOperationException()
        set(value) = head.meta("viewport", value)
}