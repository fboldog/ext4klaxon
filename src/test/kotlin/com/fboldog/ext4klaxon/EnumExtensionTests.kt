package com.fboldog.ext4klaxon

import com.beust.klaxon.JsonObject
import org.testng.annotations.Test
import kotlin.test.assertEquals
import kotlin.test.fail

class EnumExtensionTests {

    enum class OneTwo {
        ONE, TWO
    }

    @Test
    fun enumValueFromValues() {
        val src = JsonObject(mapOf(Pair("string_to_enum", "ONE")))
        assertEquals(OneTwo.ONE, src.enumFromValues("string_to_enum", OneTwo.values()))
    }

    @Test
    fun enumValueFromEnum() {
        val src = JsonObject(mapOf(Pair("string_to_enum", "ONE")))
        assertEquals(OneTwo.ONE, src.enum<OneTwo>("string_to_enum"))
    }

    //expectedExceptionsMessageRegExp = "java.lang.Integer cannot be cast to com.fboldog.klaxontypeext.TypeExtensionTests\$OneTwo"
    @Test(expectedExceptions = arrayOf(java.lang.ClassCastException::class))
    fun enumValueFromEnumFail() {
        val src = JsonObject(mapOf(Pair("string_to_enum", Int.MAX_VALUE)))
        src.enum<OneTwo>("string_to_enum")
        fail("cannot be there")
    }

    @Test
    fun enumValueFromFunction(){
        val src = JsonObject(mapOf(Pair("string_to_enum", "ONE")))
        fun getOneTwo(name: String) : OneTwo = OneTwo.valueOf(name)
        src.enumFromFunction("string_to_enum", {ref->getOneTwo(ref)})
    }
}