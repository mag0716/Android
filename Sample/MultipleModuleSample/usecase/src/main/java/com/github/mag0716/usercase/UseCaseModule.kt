package com.github.mag0716.usercase

import com.github.mag0716.datasource.IDataRepository

fun provideGetDataListUseCase(dataRepository: IDataRepository): IGetDataListUseCase {
    return GetDataListUseCase(dataRepository)
}

fun provideGetDataDetailUseCase(dataRepository: IDataRepository): IGetDataDetailUseCase {
    return GetDataDetailUseCase(dataRepository)
}