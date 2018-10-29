package com.github.mag0716.usercase

import com.github.mag0716.datasource.IDataRepository
import com.github.mag0716.datasource.model.Data

internal class GetDataListUseCase(private val repository: IDataRepository) : IGetDataListUseCase {
    override suspend fun execute(): List<Data> {
        println("GetDataListUseCase($this) : repository = $repository")
        return repository.fetchDataListOrCache()
    }
}