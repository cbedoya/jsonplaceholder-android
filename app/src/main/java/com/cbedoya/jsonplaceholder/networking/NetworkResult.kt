package com.cbedoya.jsonplaceholder.networking

sealed class NetworkResult<T>

data class Running<T>(val loading: Boolean = true) : NetworkResult<T>()

data class Success<T>(val data: T) : NetworkResult<T>()

data class Failure<T>(val error: Throwable) : NetworkResult<T>()
