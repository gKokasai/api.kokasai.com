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

    val document
        get() = group.flatMap { Group.get(it).document }

    companion object : WithJsonFile.Companion<String, User>() {
        inline val User.isAdmin
            get() = group.contains(Group.Name.admin)

        override fun create(key: String) = User(key)
    }
}
