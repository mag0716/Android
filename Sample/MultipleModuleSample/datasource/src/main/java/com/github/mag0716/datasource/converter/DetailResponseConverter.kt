package com.github.mag0716.datasource.converter

import com.github.mag0716.api.response.DetailResponse
import com.github.mag0716.datastore.model.Detail

internal fun DetailResponse.toDetail(): Detail {
    return Detail(
            this.id,
            this.title,
            this.description,
            this.image,
            this.createdTime
    )
}