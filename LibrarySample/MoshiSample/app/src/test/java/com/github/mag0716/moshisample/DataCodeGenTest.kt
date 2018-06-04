package com.github.mag0716.moshisample

import com.squareup.moshi.Moshi
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Test

class DataCodeGenTest {

    private val moshi = Moshi.Builder().build()

    @Test
    fun DataWithImpl() {
        val adapter = DataWithImplCodeGen.Companion.jsonAdapter(moshi)
        val data = adapter.fromJson(generateTestString())

        if (data != null) {
            Assert.assertThat(data.name, Is.`is`("dummy"))
            Assert.assertThat(data.id, Is.`is`(1))
        } else {
            Assert.fail("data is null")
        }
    }

    @Test
    fun DataWithDelegate() {
        val adapter = DataWithDelegateCodeGen.Companion.jsonAdapter(moshi)
        val data = adapter.fromJson(generateTestString())

        if (data != null) {
            Assert.assertThat(data.name, Is.`is`("dummy"))
            Assert.assertThat(data.id, Is.`is`(1))
        } else {
            Assert.fail("data is null")
        }
    }

    private fun generateTestString() = """
        {
            "id":1,
            "name":"dummy"
        }
        """
}