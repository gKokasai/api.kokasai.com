package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.header
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.apiRoute() {
    route("/api") {
        route("/bus") {
            get("/challenge") {
                BusTokenManager.challenge(call) {
                    call.respond(HttpStatusCode.OK, "Succeed Challenge")
                }
            }
            get("/route") {
                BusTokenManager.challenge(call) {
                    call.respond(BusRoute.route)
                }
            }
        }
    }
}

object BusTokenManager {
    suspend inline fun challenge(call: ApplicationCall, onSuccess: () -> Unit) {
        if(checkValid(call)){
            onSuccess.invoke()
        } else {
            call.respond(HttpStatusCode.Forbidden, "Failed Challenge")
        }
    }

    fun checkValid(call: ApplicationCall): Boolean {
        return checkValid(call.request.header("token"))
    }

    private fun checkValid(token: String?): Boolean {
        return token == "abcdef" // TODO 仮のトークン確認
    }
}

object BusRoute {
    data class RoutePoint(
        // 地点名
        val name: String,
        // 緯度
        val latitude: Double,
        // 経度
        val longitude: Double
    )

    data class Route(
        val fromPoint: RoutePoint,
        val toPoint: RoutePoint
    )

    val route = Route(
        RoutePoint(
            "新前橋駅",
            36.380134,
            139.047118
        ),
        RoutePoint(
            "群馬高専",
            36.377756,
            139.024364
        )
    )
}