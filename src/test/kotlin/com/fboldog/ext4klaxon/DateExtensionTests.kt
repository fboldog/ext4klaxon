package com.fboldog.ext4klaxon;

import com.beust.klaxon.JsonObject
import org.testng.annotations.Test
import java.util.*
import kotlin.test.assertEquals

public class DateExtensionTests {

    @Test
    fun dateValueFromLong() {
        val input: Long = java.lang.System.currentTimeMillis() / 1000
        val src = JsonObject(mapOf(Pair("long_to_date", input)))
        assertEquals(Date(input.times(1000)),src.date("long_to_date"))
    }

    @Test
    fun dateValueFromString() {
        val input: String = (java.lang.System.currentTimeMillis() / 1000).toString()
        val src = JsonObject(mapOf(Pair("string_to_date", input)))
        assertEquals(Date(input.toLong().times(1000)),src.date("string_to_date"))
    }
}
