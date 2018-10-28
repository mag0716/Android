package com.github.mag0716.usercase

import com.github.mag0716.datastore.model.Detail

interface IGetDataDetailUseCase {
    suspend fun execute(id: Int): Detail
}