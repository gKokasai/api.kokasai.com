package com.kokasai.flowerkt.env

import kotlin.reflect.KProperty

abstract class SystemEnvValueType<T>(private val key: String) {
    open operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return System.getenv(key).let(::convert)
    }

    abstract fun convert(value: String): T
}
