package com.github.mag0716.usercase

import com.github.mag0716.datasource.Repository
import com.github.mag0716.datastore.model.Detail

class GetDataDetailUseCase(private val repository: Repository) {
    suspend fun execute(id: Int): Detail = repository.fetchDataDetailOrCache(id)
}