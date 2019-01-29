package com.github.mag0716.usercase

import com.github.mag0716.datasource.IDataRepository
import com.github.mag0716.datastore.model.Detail
import timber.log.Timber
import timber.log.debug

internal class GetDataDetailUseCase(private val repository: IDataRepository) : IGetDataDetailUseCase {
    override suspend fun execute(id: Int): Detail {
        Timber.debug { "GetDataDetailUseCase($this) : repository = $repository" }
        return repository.fetchDataDetailOrCache(id)
    }
}