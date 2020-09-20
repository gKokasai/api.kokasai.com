package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.the23rd.extension.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.apiRoute() {
    route(HtmlRoute.Api) {
        route(HtmlRoute.Api.Bus) {
            route(HtmlRoute.Api.Bus.Challenge) {
                get {
                    BusTokenManager.challenge(call) {
                        call.respond(HttpStatusCode.OK, it)
                    }
                }
            }
            route(HtmlRoute.Api.Bus.Route) {
                get {
                    BusTokenManager.challenge(call) {
                        call.respond(BusRoute.route)
                    }
                }
            }
        }
    }
}

object BusTokenManager {
    private val busList = mapOf(
        "abcdef" to "abc" // TODO 仮のトークン確認
    )

    suspend inline fun challenge(call: ApplicationCall, onSuccess: (String) -> Unit) {
        find(call)?.let(onSuccess) ?: run {
            call.respond(HttpStatusCode.Forbidden, "null")
        }
    }

    fun find(call: ApplicationCall): String? {
        return find(call.request.header("token"))
    }

    private fun find(token: String?): String? {
        return busList[token]
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