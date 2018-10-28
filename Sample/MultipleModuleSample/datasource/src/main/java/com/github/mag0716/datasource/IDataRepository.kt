package com.github.mag0716.datasource

import com.github.mag0716.datasource.model.Data
import com.github.mag0716.datastore.model.Detail

interface IDataRepository {
    suspend fun fetchDataListOrCache(): List<Data>

    suspend fun fetchDataDetailOrCache(id: Int): Detail
}