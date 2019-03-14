package com.github.mag0716.usercase

import com.github.mag0716.multimodulesample.datasource.IDataRepository
import com.github.mag0716.multimodulesample.datasource.model.Data
import timber.log.Timber
import timber.log.debug

internal class GetDataListUseCase(private val repository: IDataRepository) : IGetDataListUseCase {
    override suspend fun execute(): List<Data> {
        Timber.debug { "GetDataListUseCase($this) : repository = $repository" }
        return repository.fetchDataListOrCache()
    }
}