package com.coroutinedemo.network

import com.coroutinedemo.model.ErrorResponse


/**
 * Created by Shivang Goel
 */
/**
 * A wrapper for database and network states.
 */
sealed class ResultState<T> {

    /**
     * A state that shows the [data] is the last known update.
     */
    data class Success<T>(val data: T) : ResultState<T>()

    /**
     * A state to show a [throwable] is thrown.
     */
    data class Error<T>(val throwable: Throwable, val errorResponse: ErrorResponse) : ResultState<T>()
}