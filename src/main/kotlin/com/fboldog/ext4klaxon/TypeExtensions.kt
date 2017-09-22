package com.fboldog.ext4klaxon

import com.beust.klaxon.JsonObject
import java.util.*
import kotlin.text.Regex

/**
 * This [this method][intStrict] returns proper conversion to [kotlin.Int]
 * from this types [kotlin.Int], [kotlin.Long], [kotlin.Double], [kotlin.Float],
 * [kotlin.String]:
 * @param fieldName [kotlin.String] required value name
 * @return [kotlin.Int] of value from field
 */
fun JsonObject.intStrict(fieldName: String) : Int? {
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

/**
 * This [this method][longStrict] returns proper conversion to [kotlin.Long]
 * from this types [kotlin.Long], [kotlin.Int], [kotlin.Double], [kotlin.Float],
 * [kotlin.String]:
 * @param fieldName [kotlin.String] required value name
 * @return [kotlin.Long] of value from field
 */
fun JsonObject.longStrict(fieldName: String) : Long? {
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

/**
 * This [this method][enum] returns proper conversion to [kotlin.Enum]
 * from type [kotlin.String]
 * @param fieldName [kotlin.String] required value name
 * @param type [kotlin.Array] list of Enum<T> values
 * @return [kotlin.Enum] of value from field
 */
fun <T: Enum<T>> JsonObject.enum(fieldName: String, type: Array<T>): T = type.single { get(fieldName) == it.name }

/**
 * This [this method][enum] returns proper conversion to [kotlin.Enum]
 * from type [kotlin.String]
 * @param fieldName [kotlin.String] required value name
 * @return [kotlin.Enum] of value from field
 */
inline fun <reified T: Enum<T>> JsonObject.enum(fieldName: String): T {
    val value = get(fieldName)
    return when (value) {
        is String -> T::class.java.enumConstants.single { value == it.name }
        else -> value as T
    }
}


/**
 * This [this method][enumFromFunction] returns proper conversion to [kotlin.Enum]
 * from type [kotlin.String]
 * @param fieldName [kotlin.String] required value name
 * @param function funtion reference which is takes [kotlin.String] and return [kotlin.Enum]
 * @return [kotlin.Enum] of value from field
 */
@Suppress("UNCHECKED_CAST")
fun <T: Enum<T>> JsonObject.enum(fieldName: String, function: (String) -> T): T {
    val value = get(fieldName)
    return when (value) {
        is String -> function(value)
        else -> value as T
    }
}

/**
 * This [this method][date] returns proper conversion to [java.util.Date]
 * from type [kotlin.String] or [kotlin.Number]
 * @param fieldName [kotlin.String] required value name
 * @return [java.util.Date] of value from field
 */
fun JsonObject.date(fieldName: String) : Date? {
    val value = get(fieldName)
    return when (value) {
        is String -> if(value.matches(Regex("^[0-9]+$"))) Date(longStrict(fieldName)!!.times(1000))
            else throw ClassCastException("value (String) is not convertable to Date by Long")
        is Number -> Date(longStrict(fieldName)!!.times(1000))
        else -> value as Date
    }
}
