package com.kokasai.api.http.group.form

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormDefineType
import com.kokasai.api.form.FormSave
import com.kokasai.api.form.FormSaveValue
import com.kokasai.api.user.User
import com.kokasai.api.util.serialize.DateSerializer
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class GetResponse(
    val name: String,
    val description: String,
    @Serializable(with = DateSerializer::class) val receive: Date,
    @Serializable(with = DateSerializer::class) val limit: Date,
    @Serializable(with = DateSerializer::class) val update: Date,
    val values: Map<Int, Value>,
    val comment: String,
    val status: Int
)

@Serializable
data class Value(
    val name: String,
    val description: String,
    val type: FormDefineType,
    val value: FormSaveValue?
)

val get: RouteAction = {
    get("{groupName}/{formName}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["groupName"]
            val formName = call.parameters["formName"]
            if (groupName != null && formName != null) {
                val formDefine = FormDefine.get(formName).file
                if (formDefine.group.contains(groupName)) {
                    val userName = principal.name
                    val user = User.get(userName)
                    val groups = user.file.group
                    if (groups.contains(groupName)) {
                        val formSave = FormSave.get(formName, groupName).file
                        val response = GetResponse(
                            formDefine.name,
                            formDefine.description,
                            formDefine.receive,
                            formDefine.limit,
                            formSave.update,
                            formDefine.values.mapValues { (index, defineValue) ->
                                Value(
                                    defineValue.name,
                                    defineValue.description,
                                    defineValue.type,
                                    formSave.values[index]
                                )
                            },
                            formSave.comment,
                            formSave.status
                        )
                        call.respond(response)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        } else {
            call.respond(HttpStatusCode.Unauthorized)
        }
    }
}
