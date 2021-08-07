package com.kokasai.api.http

import com.kokasai.api.KokasaiApi.Companion.api
import com.kokasai.api.http._dsl.onlyAdmin
import com.kokasai.api.http._dsl.parameters
import com.kokasai.api.util.call.receiveFile
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.response.respondFile
import io.ktor.util.pipeline.PipelineInterceptor

val webdavGet: PipelineInterceptor<Unit, ApplicationCall> = {
    onlyAdmin {
        parameters("path") {
            val path = it.joinToString("/")
            val file = api.fileProvider.get(path)
            if (file != null) {
                call.respondFile(file)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

val webdavPost: PipelineInterceptor<Unit, ApplicationCall> = {
    onlyAdmin {
        parameters("path") { path ->
            val file = call.receiveFile()
            if (file != null) {
                api.fileProvider.add(path.joinToString("/"), file)
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}

val webdavDelete: PipelineInterceptor<Unit, ApplicationCall> = {
    onlyAdmin {
        parameters("path") { path ->
            api.fileProvider.remove(path.joinToString("/"))
        }
    }
}

val webdavMkcol: PipelineInterceptor<Unit, ApplicationCall> = {
    onlyAdmin {
        parameters("path") { path ->
            api.fileProvider.mkdir(path.joinToString("/"))
        }
    }
}
