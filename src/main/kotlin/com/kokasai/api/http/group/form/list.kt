package com.kokasai.api.http.group.form

import com.kokasai.api.form.FormDefine
import com.kokasai.api.form.FormSave
import com.kokasai.api.group.Group
import com.kokasai.api.http._dsl.inGroupFromParameter
import com.kokasai.api.util.serialize.DateSerializer
import com.kokasai.flowerkt.route.RouteAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.get
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class GetListResponse(val form: Map<String, SimpleGroupFormData>)

@Serializable
data class SimpleGroupFormData(
    val name: String,
    @Serializable(with = DateSerializer::class) var update: Date,
    val status: Int
)

val list: RouteAction = {
    get("{groupName}") {
        inGroupFromParameter("groupName") { _, groupName ->
            call.respond(getGroupFormListResponse(groupName))
        }
    }
}

suspend fun getGroupFormListResponse(groupName: String) = Group.get(groupName).run {
    GetListResponse(
        file.form.associateWith { formName ->
            val formDefine = FormDefine.get(formName).file
            val formSave = FormSave.get(formName, groupName).file
            SimpleGroupFormData(
                formDefine.name,
                formSave.update,
                formSave.status
            )
        }
    )
}
