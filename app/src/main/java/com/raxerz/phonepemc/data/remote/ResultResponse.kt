package com.raxerz.phonepemc.data.remote

sealed class ResultResponse<out T> {

    data class Success<T>(val data: T) : ResultResponse<T>()
    data class Error<T>(val message: String) : ResultResponse<T>()

    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(message: String) = Error<T>(message)
    }
}
