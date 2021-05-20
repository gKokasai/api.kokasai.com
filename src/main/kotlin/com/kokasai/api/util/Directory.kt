package com.kokasai.api.util

import com.kokasai.api.KokasaiApi.Companion.api

object Directory {
    const val group = "group"
    const val form = "form"
    const val user = "user"
    const val document = "document"

    suspend fun create() {
        listOf(group, form, user, document).forEach {
            api.fileProvider.mkdir(it)
        }
    }
}
