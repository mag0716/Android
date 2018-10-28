package com.github.mag0716.datasource;

import com.github.mag0716.api.ApiClientFactory
import com.github.mag0716.api.ApiService
import com.github.mag0716.api.response.DataResponse
import com.github.mag0716.api.response.DetailResponse
import com.github.mag0716.datasource.converter.toDataList
import com.github.mag0716.datasource.converter.toDetail
import com.github.mag0716.datasource.model.Data
import com.github.mag0716.datastore.model.Detail
import kotlinx.coroutines.experimental.CoroutineScope

class Repository(private val coroutineScope: CoroutineScope) {

    private val apiService: ApiService = ApiClientFactory(coroutineScope).create()

    // TODO: キャッシュ機能
    // 変換したデータをキャッシュすべき？
    private var dataListCached: List<DataResponse>? = null
    private val detailMap = mutableMapOf<Int, DetailResponse>()

    // TODO:リフレッシュ機構

    suspend fun fetchDataListOrCache(): List<Data> {
        if (dataListCached == null) {
            dataListCached = apiService.data().await()
        }
        return checkNotNull(dataListCached).toDataList()
    }

    suspend fun fetchDataDetailOrCache(id: Int): Detail {
        if (detailMap.contains(id).not()) {
            val detail = apiService.detail(id).await()
            detailMap[id] = detail
        }
        return checkNotNull(detailMap[id]).toDetail()
    }
}
