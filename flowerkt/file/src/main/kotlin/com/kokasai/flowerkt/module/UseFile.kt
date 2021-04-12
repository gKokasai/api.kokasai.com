package com.kokasai.flowerkt.module

import com.kokasai.flowerkt.file.FileProvider

interface UseFile : UseModule {
    /**
     * ファイルの保存先
     */
    val fileProvider: FileProvider
}
