package com.github.mag0716.datasource;

import com.github.mag0716.api.ApiService
import com.github.mag0716.api.model.Data
import com.github.mag0716.api.model.Detail

class Repository(private val apiService: ApiService) {

    private var dataListCached: List<Data>? = null
    private val detailMap = mutableMapOf<Int, Detail>()

    // TODO:リフレッシュ機構

    suspend fun loadDataList(): List<Data> {
        if (dataListCached == null) {
            dataListCached = apiService.data().await()
        }
        return checkNotNull(dataListCached)
    }

    suspend fun loadDataDetail(id: Int): Detail {
        if (detailMap.contains(id)) {
            return checkNotNull(detailMap[id])
        }
        return apiService.detail(id).await()
    }
}
