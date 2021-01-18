package com.kokasai.flowerkt.database

import org.jetbrains.exposed.sql.*

class SQLiteDatabaseProvider(val fileName: String): DatabaseProvider {
    override fun connect() {
        Database.connect("jdbc:sqlite:$fileName")
    }
}