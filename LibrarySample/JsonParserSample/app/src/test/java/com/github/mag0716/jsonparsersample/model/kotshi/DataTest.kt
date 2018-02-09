package com.github.mag0716.jsonparsersample.model.kotshi

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

/**
 * Created by mag0716 on 2018/02/07.
 */
class DataTest {

    lateinit var moshi: Moshi

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
                .add(ApplicationJsonAdapterFactory.INSTANCE)
                .add(DateConverter())
                .build()
    }

    @Test
    fun 正常にパースされること() {
        val json = """
            {
                "id":1,
                "text":"text",
                "nullableText":"nullableText",
                "custom_name":"custom_name",
                "datetime":"20180101000000"
            }
            """.trimIndent()
        val actual = moshi.adapter(Data::class.java).fromJson(json)
        assertThat(actual, `is`(notNullValue()))
        actual?.apply {
            assertThat(id, `is`(1))
            assertThat(text, `is`("text"))
            assertThat(nullableText, `is`("nullableText"))
            assertThat(customName, `is`("custom_name"))
            assertThat(datetime.time, `is`(1514732400000L))
        }
    }

    @Test
    fun Nullableな値がnullでも正常にパースされること() {
        val json = """
            {
                "id":1,
                "text":"text",
                "nullableText":null,
                "custom_name":"custom_name",
                "datetime":"20180101000000"
            }
            """.trimIndent()
        val actual = moshi.adapter(Data::class.java).fromJson(json)
        assertThat(actual, `is`(notNullValue()))
        actual?.apply {
            assertThat(id, `is`(1))
            assertThat(text, `is`("text"))
            assertThat(nullableText, `is`(nullValue()))
            assertThat(customName, `is`("custom_name"))
            assertThat(datetime.time, `is`(1514732400000L))
        }
    }

    @Test(expected = JsonDataException::class)
    fun カスタムConverterの変換に失敗した場合は例外が発生すること() {
        val json = """
            {
                "id":1,
                "text":"text",
                "nullableText":"nullableText",
                "custom_name":"custom_name",
                "datetime":"2018/01/01 00:00:00"
            }
            """.trimIndent()
        val actual = moshi.adapter(Data::class.java).fromJson(json)
        fail("expect ")
    }

    @Test(expected = NullPointerException::class)
    fun NonNullな値がnullの場合は例外が発生すること() {
        val json = """
            {
                "id":1,
                "text":null,
                "nullableText":"nullableText",
                "custom_name":"custom_name",
                "datetime":"20180101000000"
            }
            """.trimIndent()
        val actual = moshi.adapter(Data::class.java).fromJson(json)
        fail("expect ")
    }
}