package com.nnapps.jettriviaapp.data

data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var isLoading: Boolean? = null,
    var e: E? = null)