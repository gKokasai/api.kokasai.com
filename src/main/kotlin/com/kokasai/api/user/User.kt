package com.kokasai.api.user

import com.kokasai.api.group.Group
import com.kokasai.api.util.Directory
import com.kokasai.api.util.json.WithJsonFile

data class User(
    val name: String
) : WithJsonFile<UserFile>(
    "${Directory.user}/$name.json",
    UserFile.Companion
) {
    val group by file::group

    suspend fun getGroup() = group.map { Group.get(it) }

    suspend fun getDocument() = getGroup().map { it.document }.flatten()

    companion object {
        inline val User.isAdmin
            get() = group.contains(Group.Name.admin)

        suspend fun get(name: String) = User(name).apply {
            load()
        }
    }
}
