package com.kokasai.api.http.group.form

import com.kokasai.api.auth.UserLogin
import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormSave
import com.kokasai.api.form.FormSaveType
import com.kokasai.api.form.FormSaveValue
import com.kokasai.api.user.User
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class PostSubmitRequest(val values: Map<Int, FormSaveType>)

val submit: RouteAction = {
    post("{groupName}/{formName}") {
        val principal = call.sessions.get<UserLogin.Data>()
        if (principal != null) {
            val groupName = call.parameters["groupName"]
            val formName = call.parameters["formName"]
            if (groupName != null && formName != null) {
                val formDefine = FormDefine.get(formName)
                if (formDefine.file.group.contains(groupName)) {
                    val userName = principal.name
                    val user = User.get(userName)
                    val groups = user.file.group
                    if (groups.contains(groupName)) {
                        val request = call.receive<PostSubmitRequest>()
                        val formSave = FormSave.get(formName, groupName)
                        val lastValues = formSave.file.values
                        if (lastValues != request.values) {
                            formSave.file.run {
                                update = Date()
                                values = request.values.mapValues { (index, type) ->
                                    val comment = lastValues[index]?.comment.orEmpty()
                                    FormSaveValue(type, comment)
                                }
                            }
                            formSave.save()
                        }
                        call.respond(HttpStatusCode.OK)
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
