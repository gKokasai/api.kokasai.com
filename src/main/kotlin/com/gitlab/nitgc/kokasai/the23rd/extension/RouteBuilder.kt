package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.routes.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

interface RouteBuilder {
    fun build(route: io.ktor.routing.Route)

    interface Container: RouteBuilder {
        val routes: Map<HtmlRoute.Path, RouteBuilder>

        override fun build(route: io.ktor.routing.Route) {
            routes.forEach { (path, builder) ->
                route.route(path.path) {
                    builder.build(this)
                }
            }
        }
    }

    class Route(val action: io.ktor.routing.Route.() -> Unit): RouteBuilder {
        override fun build(route: io.ktor.routing.Route) = route.action()
    }
}

@ContextDsl
fun route(action: Route.() -> Unit) = RouteBuilder.Route(action)

fun authenticate(
    vararg configurations: String? = arrayOf(null),
    optional: Boolean = false,
    build: Route.() -> Unit
) = route { authenticate(*configurations, optional = optional, build = build) }

@ContextDsl
fun get(body: PipelineInterceptor<Unit, ApplicationCall>) = route { get(body) }