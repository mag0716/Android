package com.github.mag0716.moshisample

data class DataWithDelegate(val name: String) : Common by CommonImpl()