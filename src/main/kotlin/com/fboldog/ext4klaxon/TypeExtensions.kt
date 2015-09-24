package com.fboldog.ext4klaxon

import com.beust.klaxon.JsonObject

public fun JsonObject.intStrict(fieldName: String) : Int? {
    val value = get(fieldName)
    return when (value) {
        is Long -> if (value <= Int.MAX_VALUE && value >= Int.MIN_VALUE) value.toInt()
            else throw ClassCastException("value (Long) is too big to convert to Int")
        is Double -> if (Math.rint(value) == value && value <= Int.MAX_VALUE && value >= Int.MIN_VALUE) value.toInt()
            else throw ClassCastException("value (Double) is not whole or too big to convert to Int")
        is Float -> if (Math.rint(value.toDouble()) == value.toDouble() && value <= Int.MAX_VALUE && value >= Int.MIN_VALUE) value.toInt()
            else throw ClassCastException("value (Float) is not whole or too big to convert to Int")
        is String -> java.lang.Integer.valueOf(value).toInt()
        else -> value as Int?
    }
}

public fun JsonObject.longStrict(fieldName: String) : Long? {
    val value = get(fieldName)
    return when (value) {
        is Int -> value.toLong()
        is Double -> if (Math.rint(value) == value && value <= Long.MAX_VALUE && value >= Long.MIN_VALUE) value.toLong()
            else throw ClassCastException("value (Double) is not whole or too big to convert to Long")
        is Float -> if (Math.rint(value.toDouble()) == value.toDouble() && value <= Long.MAX_VALUE && value >= Long.MIN_VALUE) value.toLong()
            else throw ClassCastException("value (Float) is not whole or too big to convert to Long")
        is String -> java.lang.Long.valueOf(value).toLong()
        else -> value as Long?
    }
}

fun <T: Enum<T>> JsonObject.enumFromValues(fieldName: String, type: Array<T>): Enum<T> = type.single { get(fieldName) == it.name() }

inline fun <reified T: Enum<T>> JsonObject.enum(fieldName: String): Enum<T> {
    val value = get(fieldName)
    return when(value){
        is String -> T::class.java.enumConstants.single {value == it.name()}
        else -> value as T
    }
}
