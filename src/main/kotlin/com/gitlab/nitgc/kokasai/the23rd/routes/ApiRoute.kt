package com.gitlab.nitgc.kokasai.the23rd.routes

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.route

fun Routing.apiRoute() {
    route("/api") {
        route("/bus") {
            get("/route") {
                call.respond(BusRoute.route)
            }
        }
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