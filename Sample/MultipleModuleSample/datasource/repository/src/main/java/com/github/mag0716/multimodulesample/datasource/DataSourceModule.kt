package com.github.mag0716.multimodulesample.datasource

import com.github.mag0716.api.ApiService

object DataSourceModule {

    fun provide(apiService: ApiService): IDataRepository {
        return DataRepository(apiService)
    }
}