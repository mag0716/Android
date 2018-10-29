package com.github.mag0716.usercase

import com.github.mag0716.datasource.DataSourceModule
import kotlinx.coroutines.experimental.CoroutineScope

fun injectGetDataListUseCase(coroutineScope: CoroutineScope): IGetDataListUseCase {
    return GetDataListUseCase(DataSourceModule.inject(coroutineScope))
}

fun injectGetDataDetailUseCase(coroutineScope: CoroutineScope): IGetDataDetailUseCase {
    return GetDataDetailUseCase(DataSourceModule.inject(coroutineScope))
}