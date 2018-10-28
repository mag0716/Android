package com.github.mag0716.usercase

import com.github.mag0716.datasource.Repository

class GetDataDetailUseCase(private val repository: Repository) {
    suspend fun execute(id: Int) = repository.loadDataDetail(id)
}