package com.github.mag0716.usercase

import com.github.mag0716.datasource.DataSourceModule
import kotlinx.coroutines.experimental.CoroutineScope

fun provideGetDataListUseCase(coroutineScope: CoroutineScope): IGetDataListUseCase {
    return GetDataListUseCase(DataSourceModule.provide(coroutineScope))
}

fun provideGetDataDetailUseCase(coroutineScope: CoroutineScope): IGetDataDetailUseCase {
    return GetDataDetailUseCase(DataSourceModule.provide(coroutineScope))
}