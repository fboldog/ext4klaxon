package com.fboldog.ext4klaxon

import com.beust.klaxon.JsonObject
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class IntExtensionTests {

    @Test
    fun longToInt() {
        val src = JsonObject(mapOf(Pair("item", Int.MAX_VALUE.toLong())))
        assertEquals(Int.MAX_VALUE, src.intStrict("item"))
    }

    @Test
    fun doubleToInt() {
        val src = JsonObject(mapOf(Pair("item", Int.MAX_VALUE.toDouble())))
        assertEquals(Int.MAX_VALUE, src.intStrict("item"))
    }

    @Test
    fun stringToInt() {
        val src = JsonObject(mapOf(Pair("item", "${Int.MAX_VALUE}")))
        assertEquals(Int.MAX_VALUE, src.intStrict("item"))
    }

    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "value \\(Long\\) is too big to convert to Int")
    fun longToIntFail() {
        val src = JsonObject(mapOf(Pair("item", Int.MAX_VALUE.toLong().inc())))
        src.intStrict("item")
        fail("cannot be there")
    }


    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "value \\(Float\\) is not whole or too big to convert to Int")
    fun floatToIntFail() {
        val src = JsonObject(mapOf(Pair("item", Float.MAX_VALUE)))
        src.intStrict("item")
        fail("cannot be there")
    }

    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "value \\(Double\\) is not whole or too big to convert to Int")
    fun doubleToIntFail() {
        val src = JsonObject(mapOf(Pair("item", Double.MAX_VALUE)))
        src.intStrict("item")
        fail("cannot be there")
    }

    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class), expectedExceptionsMessageRegExp = "java.lang.Boolean cannot be cast to java.lang.Integer")
    fun toIntFail() {
        val src = JsonObject(mapOf(Pair("item", false)))
        src.intStrict("item")
        fail("cannot be there")
    }
}