package com.github.mag0716.datasource.converter

import com.github.mag0716.api.response.DataResponse
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class DataResponseConverterTest {

    @Test
    fun toData_正しく変換できること() {
        val dataResponse = DataResponse(1, "title", "thumbnail")
        val data = dataResponse.toData()
        assertThat(data.id, `is`(1))
        assertThat(data.title, `is`("title"))
        assertThat(data.thumbnail, `is`("thumbnail"))
    }

    @Test
    fun toDataList_正しく変換できること() {
        val dataResponseList = listOf(
            DataResponse(1, "title1", "thumbnail1"),
            DataResponse(2, "title2", "thumbnail2"),
            DataResponse(3, "title3", "thumbnail3")
        )
        val dataList = dataResponseList.toDataList()
        assertThat(dataList.size, `is`(3))
    }
}