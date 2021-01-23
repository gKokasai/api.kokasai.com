package com.kokasai.flowerkt.env

interface SystemEnvContainer {
    fun string(key: String) = object : SystemEnvValueType<String>(key) {
        override fun convert(value: String) = value
    }

    fun stringOrNull(key: String) = object : SystemEnvValueTypeNullable<String>(key) {
        override fun convert(value: String) = value
    }

    fun int(key: String) = object : SystemEnvValueType<Int>(key) {
        override fun convert(value: String) = value.toInt()
    }

    fun intOrNull(key: String) = object : SystemEnvValueTypeNullable<Int>(key) {
        override fun convert(value: String) = value.toIntOrNull()
    }
}
