package com.naci.sample.themetmuseum.data

import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

data class ApiError(var code: Int, var message: String)

sealed class Resource<out T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error(val apiError: ApiError) : Resource<Nothing>()
    class Loading(val isLoading: Boolean) : Resource<Nothing>()
    object Empty : Resource<Nothing>()
}

fun <T> serviceRequestFlow(
    modifyFun: ((Resource<T>) -> Resource<T>)? = null,
    suspendFun: suspend () -> T
): Flow<Resource<T>> {
    return flow {
        try {
            val result = suspendFun()
            emitSuccess(result, modifyFun)
        } catch (httpE: HttpException) {
            val apiError = ApiError(
                code = httpE.code(),
                message = httpE.message()
            )
            emitError(apiError, modifyFun)
        } catch (unknownHostE: UnknownHostException) {
            val apiError = ApiError(
                code = -101,
                message = unknownHostE.message.toString()
            )
            emitError(apiError, modifyFun)
        } catch (socketE: SocketException) {
            val apiError = ApiError(
                code = -102,
                message = socketE.message.toString()
            )
            emitError(apiError, modifyFun)
        } catch (socketTimeoutE: SocketTimeoutException) {
            val apiError = ApiError(
                code = -103,
                message = socketTimeoutE.message.toString()
            )
            emitError(apiError, modifyFun)
        } catch (e: Exception) {
            val apiError = ApiError(
                code = -100,
                message = e.message.toString()
            )
            emitError(apiError, modifyFun)
        }
    }.onStart {
        emit(Resource.Loading(true))
    }.onCompletion {
        emit(Resource.Loading(false))
    }
}

fun <T, R> serviceRequestTransformFlow(
    suspendFun: suspend () -> T,
    transform: ((T) -> R)
): Flow<Resource<R>> {
    return flow {
        try {
            val result = suspendFun()
            val transformed = transform(result)
            emit(Resource.Success(transformed))
        } catch (httpE: HttpException) {
            val apiError = ApiError(
                code = httpE.code(),
                message = httpE.message()
            )
            emit(Resource.Error(apiError))
        } catch (unknownHostE: UnknownHostException) {
            val apiError = ApiError(
                code = -101,
                message = unknownHostE.message.toString()
            )
            emit(Resource.Error(apiError))
        } catch (socketE: SocketException) {
            val apiError = ApiError(
                code = -102,
                message = socketE.message.toString()
            )
            emit(Resource.Error(apiError))
        } catch (socketTimeoutE: SocketTimeoutException) {
            val apiError = ApiError(
                code = -103,
                message = socketTimeoutE.message.toString()
            )
            emit(Resource.Error(apiError))
        } catch (e: Exception) {
            val apiError = ApiError(
                code = -100,
                message = e.message.toString()
            )
            emit(Resource.Error(apiError))
        }
    }.onStart {
        emit(Resource.Loading(true))
    }.onCompletion {
        emit(Resource.Loading(false))
    }
}

suspend fun <T> FlowCollector<Resource<T>>.emitError(
    apiError: ApiError,
    modifyFun: ((Resource<T>) -> Resource<T>)? = null
) {
    if (modifyFun != null) {
        val modified = modifyFun(Resource.Error(apiError))
        emit(modified)
    } else {
        emit(Resource.Error(apiError))
    }
}

suspend fun <T> FlowCollector<Resource<T>>.emitSuccess(
    result: T,
    modifyFun: ((Resource<T>) -> Resource<T>)? = null
) {
    if (modifyFun != null) {
        val modified = modifyFun(Resource.Success(result))
        emit(modified)
    } else {
        emit(Resource.Success(result))
    }
}