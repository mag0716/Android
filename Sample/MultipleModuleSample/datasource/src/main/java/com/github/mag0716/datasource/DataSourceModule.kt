package com.github.mag0716.datasource

object DataSourceModule {

    private val dataRepository: IDataRepository by lazy {
        DataRepository()
    }

    fun provide(): IDataRepository {
        return dataRepository
    }
}