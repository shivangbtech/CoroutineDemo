package com.coroutinedemo.base

import com.coroutinedemo.model.ErrorResponse
import com.coroutinedemo.network.NetworkConfig
import com.coroutinedemo.network.ResultState
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by shivanggoel on 19,February,2020
 */
abstract class BaseRepositoryImpl {

    private val logFormatter: String = "%s | %s"

    internal fun handleErrorReturn(throwable: Throwable): ResultState.Error<ResultState<ErrorResponse>> {
        Timber.tag("BaseRepositoryImpl")
        return when (throwable) {
            is HttpException -> {
                when (throwable.code()) {
                    NetworkConfig.RESPONSE_CODE_NOT_FOUND -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_NOT_FOUND
                        )
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_NOT_FOUND,
                                NetworkConfig.ERROR_MESSAGE_NOT_FOUND
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_FORBIDDEN -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.RESPONSE_CODE_FORBIDDEN
                        )
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_FORBIDDEN,
                                NetworkConfig.ERROR_MESSAGE_FORBIDDEN
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_SERVICE_UNAVAILABLE -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_SERVICE_UNAVAILABLE
                        )
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_SERVICE_UNAVAILABLE,
                                NetworkConfig.ERROR_MESSAGE_SERVICE_UNAVAILABLE
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_GATEWAY_TIMEOUT -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_GATEWAY_TIMEOUT
                        )
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_GATEWAY_TIMEOUT,
                                NetworkConfig.ERROR_MESSAGE_GATEWAY_TIMEOUT
                            )
                        )
                    }
                    NetworkConfig.RESPONSE_CODE_INTERNAL_SERVER_ERROR -> {
                        Timber.e(
                            logFormatter,
                            throwable.response().toString(),
                            NetworkConfig.ERROR_MESSAGE_INTERNAL_SERVER_ERROR
                        )
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_INTERNAL_SERVER_ERROR,
                                NetworkConfig.ERROR_MESSAGE_INTERNAL_SERVER_ERROR
                            )
                        )
                    }
                    else -> {
                        ResultState.Error(
                            throwable,
                            ErrorResponse(
                                NetworkConfig.RESPONSE_CODE_UNEXPECTED,
                                NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                            )
                        )
                    }
                }
            }
            is UnknownHostException, is IOException, is ConnectException, is SocketTimeoutException -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_NETWORK
                )
                ResultState.Error(
                    throwable,
                    ErrorResponse(
                        NetworkConfig.RESPONSE_CODE_NETWORK,
                        NetworkConfig.ERROR_MESSAGE_NETWORK
                    )
                )
            }
            else -> {
                Timber.e(
                    logFormatter,
                    throwable.message.toString(),
                    NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                )
                ResultState.Error(
                    throwable,
                    ErrorResponse(
                        NetworkConfig.RESPONSE_CODE_UNEXPECTED,
                        NetworkConfig.ERROR_MESSAGE_UNEXPECTED
                    )
                )
            }
        }
    }
}