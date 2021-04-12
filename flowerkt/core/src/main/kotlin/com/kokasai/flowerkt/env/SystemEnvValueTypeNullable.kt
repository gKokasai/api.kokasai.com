package com.kokasai.flowerkt.env

import kotlin.reflect.KProperty

abstract class SystemEnvValueTypeNullable<T>(private val key: String) : SystemEnvValueType<T?>(key) {
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return System.getenv(key)?.let(::convert)
    }
}
