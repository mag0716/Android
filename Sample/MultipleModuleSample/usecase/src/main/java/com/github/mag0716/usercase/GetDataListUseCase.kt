package com.github.mag0716.usercase

import com.github.mag0716.datasource.Repository

class GetDataListUseCase(private val repository: Repository) {
    suspend fun execute() = repository.fetchDataListOrCache()
}