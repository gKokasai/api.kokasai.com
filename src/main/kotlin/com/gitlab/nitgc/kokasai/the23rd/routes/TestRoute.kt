package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

data class TestDataClass(
    val int_data: Int,
    val inner: InnerTestDataClass
)

data class InnerTestDataClass(
    val int_data: Int
)

fun Routing.testRoute() {
    get("/test") {
        call.respond(TestDataClass(5, InnerTestDataClass(3)))
    }
}