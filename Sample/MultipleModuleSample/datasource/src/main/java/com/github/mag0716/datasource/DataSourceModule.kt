package com.github.mag0716.datasource

import kotlinx.coroutines.experimental.CoroutineScope

object DataSourceModule {
    fun inject(coroutineScope: CoroutineScope): IDataRepository {
        return DataRepository(coroutineScope)
    }
}