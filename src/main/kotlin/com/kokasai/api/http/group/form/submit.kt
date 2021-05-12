package com.kokasai.api.http.group.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormSave
import com.kokasai.api.form.FormSaveType
import com.kokasai.api.form.FormSaveValue
import com.kokasai.api.http._dsl.inGroup
import com.kokasai.api.http._dsl.parameter
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.post
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class PostSubmitRequest(val values: Map<Int, FormSaveType>)

val submit: RouteAction = {
    post("{groupName}/{formName}") {
        parameter("groupName", "formName") { groupName, formName ->
            inGroup(groupName) {
                val formDefine = FormDefine.get(formName)
                if (formDefine.file.group.contains(groupName)) {
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
            }
        }
    }
}
