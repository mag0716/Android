package com.github.mag0716.moshisample

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Assert.fail
import org.junit.Test

class DataTest {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Test
    fun DataWithImpl() {
        val adapter = moshi.adapter(DataWithImpl::class.java)
        val data = adapter.fromJson(generateTestString())

        if (data != null) {
            assertThat(data.name, `is`("dummy"))
            assertThat(data.id, `is`(1))
        } else {
            fail("data is null")
        }
    }

    @Test
    fun DataWithDelegate() {
        val adapter = moshi.adapter(DataWithDelegate::class.java)
        val data = adapter.fromJson(generateTestString())

        if (data != null) {
            assertThat(data.name, `is`("dummy"))
            assertThat(data.id, `is`(1))
        } else {
            fail("data is null")
        }
    }

    private fun generateTestString() = """
        {
            "id":1,
            "name":"dummy"
        }
        """
}