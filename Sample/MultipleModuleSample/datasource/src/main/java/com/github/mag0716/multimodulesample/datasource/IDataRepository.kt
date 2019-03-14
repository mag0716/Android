package com.github.mag0716.multimodulesample.datasource

import com.github.mag0716.multimodulesample.datasource.model.Data
import com.github.mag0716.multimodulesample.datastore.model.Detail

interface IDataRepository {
    suspend fun fetchDataListOrCache(): List<Data>

    suspend fun fetchDataDetailOrCache(id: Int): Detail
}