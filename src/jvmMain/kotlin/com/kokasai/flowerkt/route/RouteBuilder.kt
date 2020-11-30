package com.kokasai.flowerkt.route

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*
import io.ktor.websocket.*

typealias RouteAction = Route.() -> Unit

interface RouteBuilder {
    fun build(parent: Route)

    operator fun plus(other: RouteBuilder) = object: RouteBuilder {
        override fun build(parent: Route) {
            this@RouteBuilder.build(parent)
            other.build(parent)
        }
    }

    interface Container: RouteBuilder {
        val routes: Map<RoutePath, RouteBuilder>

        override fun build(parent: Route) {
            routes.forEach { (path, builder) ->
                parent.route(path.path) {
                    builder.build(this)
                }
            }
        }
    }

    class Element(val action: RouteAction): RouteBuilder {
        override fun build(parent: Route) = parent.action()
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