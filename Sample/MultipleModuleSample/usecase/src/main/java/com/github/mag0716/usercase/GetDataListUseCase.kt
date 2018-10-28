package com.github.mag0716.usercase

import com.github.mag0716.datasource.Repository
import com.github.mag0716.datasource.model.Data

class GetDataListUseCase(private val repository: Repository) {
    suspend fun execute(): List<Data> = repository.fetchDataListOrCache()
}