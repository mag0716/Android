package com.github.mag0716.mlkitsample

import java.io.Serializable

data class CreditCard(
    val number: String,
    val expiredDate: String
) : Serializable