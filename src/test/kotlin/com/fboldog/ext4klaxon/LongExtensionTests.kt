package com.fboldog.ext4klaxon

import com.beust.klaxon.JsonObject
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class LongExtensionTests {

    @Test
    fun intToLong() {
        val src = JsonObject(mapOf(Pair("int_to_long", Int.MAX_VALUE)))
        assertEquals(Int.MAX_VALUE.toLong(), src.longStrict("int_to_long"))
    }

    @Test
    fun doubleToLong() {
        val src = JsonObject(mapOf(Pair("double_to_long", Long.MAX_VALUE.toDouble())))
        assertEquals(Long.MAX_VALUE, src.longStrict("double_to_long"))
    }

    @Test
    fun floatToLong() {
        val src = JsonObject(mapOf(Pair("float_to_long", Long.MAX_VALUE.toFloat())))
        assertEquals(Long.MAX_VALUE, src.longStrict("float_to_long"))
    }

    @Test
    fun stringToLong() {
        val src = JsonObject(mapOf(Pair("string_to_long", "${Long.MAX_VALUE}")))
        assertEquals(Long.MAX_VALUE, src.longStrict("string_to_long"))
    }


    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "value \\(Double\\) is not whole or too big to convert to Long")
    fun doubleToLongFail() {
        val src = JsonObject(mapOf(Pair("double_to_long", 12.34)))
        src.longStrict("double_to_long")
        fail("cannot be there")
    }

    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "java.lang.Boolean cannot be cast to java.lang.Long")
    fun toLongFail() {
        val src = JsonObject(mapOf(Pair("to_long_fail", false)))
        src.longStrict("to_long_fail")
        fail("cannot be there")
    }


    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "value \\(Float\\) is not whole or too big to convert to Long")
    fun floatToLongFail() {
        val src = JsonObject(mapOf(Pair("float_to_long", 12.34f)))
        src.longStrict("float_to_long")
        fail("cannot be there")
    }
}