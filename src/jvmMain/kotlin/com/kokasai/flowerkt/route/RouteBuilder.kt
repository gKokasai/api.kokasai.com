package com.kokasai.flowerkt.route

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import io.ktor.websocket.*

typealias RouteAction = Route.() -> Unit

interface RouteBuilder {
    fun build(route: Route)

    operator fun plus(other: RouteBuilder) = object: RouteBuilder {
        override fun build(route: Route) {
            this@RouteBuilder.build(route)
            other.build(route)
        }
    }

    interface Container: RouteBuilder {
        val routes: Map<RoutePath, RouteBuilder>

        override fun build(route: Route) {
            routes.forEach { (path, builder) ->
                route.route(path.path) {
                    builder.build(this)
                }
            }
        }
    }

    class Element(val action: RouteAction): RouteBuilder {
        override fun build(route: Route) = route.action()
    }
}

@ContextDsl
fun route(action: RouteAction) = RouteBuilder.Element(action)

fun authenticate(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    build: RouteAction
) = route { authenticate(*configurations, optional = optional, build = build) }

@ContextDsl
fun get(body: PipelineInterceptor<Unit, ApplicationCall>) = route { get(body) }

@ContextDsl
fun webSocket(handler: suspend DefaultWebSocketServerSession.() -> Unit) = route { webSocket(null, handler) }