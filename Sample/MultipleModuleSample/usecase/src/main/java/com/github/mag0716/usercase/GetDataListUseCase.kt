package com.github.mag0716.usercase

import com.github.mag0716.datasource.IDataRepository
import com.github.mag0716.datasource.model.Data

// TODO: internal にする
class GetDataListUseCase(private val repository: IDataRepository) : IGetDataListUseCase {
    override suspend fun execute(): List<Data> = repository.fetchDataListOrCache()
}