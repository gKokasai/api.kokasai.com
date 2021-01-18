package com.kokasai.flowerkt.database

import org.jetbrains.exposed.sql.Database

class SQLiteDatabaseProvider(val fileName: String) : DatabaseProvider {
    override fun connect() {
        Database.connect("jdbc:sqlite:$fileName")
    }
}
