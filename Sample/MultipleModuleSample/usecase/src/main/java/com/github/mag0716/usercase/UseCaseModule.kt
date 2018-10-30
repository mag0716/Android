package com.github.mag0716.usercase

import com.github.mag0716.datasource.DataSourceModule

fun provideGetDataListUseCase(): IGetDataListUseCase {
    return GetDataListUseCase(DataSourceModule.provide())
}

fun provideGetDataDetailUseCase(): IGetDataDetailUseCase {
    return GetDataDetailUseCase(DataSourceModule.provide())
}