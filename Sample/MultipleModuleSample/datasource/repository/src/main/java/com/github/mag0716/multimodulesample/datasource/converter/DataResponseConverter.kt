package com.github.mag0716.multimodulesample.datasource.converter

import com.github.mag0716.api.response.DataResponse
import com.github.mag0716.multimodulesample.datasource.model.Data

internal fun DataResponse.toData(): Data {
    return Data(
        this.id,
        this.title,
        this.thumbnail
    )
}

internal fun List<DataResponse>.toDataList(): List<Data> {
    return this.map {
        it.toData()
    }.toList()
}