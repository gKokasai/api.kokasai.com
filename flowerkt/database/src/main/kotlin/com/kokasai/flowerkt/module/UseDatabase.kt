package com.kokasai.flowerkt.module

import com.kokasai.flowerkt.database.DatabaseProvider

interface UseDatabase : UseModule {
    /**
     * データベース
     */
    val databaseProvider: DatabaseProvider
}
