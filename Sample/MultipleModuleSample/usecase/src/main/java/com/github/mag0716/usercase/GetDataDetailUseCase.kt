package com.github.mag0716.usercase

import com.github.mag0716.datasource.IDataRepository
import com.github.mag0716.datastore.model.Detail

// TODO: internal にする
class GetDataDetailUseCase(private val repository: IDataRepository) : IGetDataDetailUseCase {
    override suspend fun execute(id: Int): Detail = repository.fetchDataDetailOrCache(id)
}