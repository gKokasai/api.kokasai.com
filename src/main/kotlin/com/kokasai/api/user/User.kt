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
    companion object {
        inline val User.isAdmin
            get() = file.group.contains(Group.Name.admin)

        suspend fun get(name: String) = User(name).apply {
            load()
        }
    }
}
