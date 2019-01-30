package com.cbedoya.jsonplaceholder.extensions

import com.cbedoya.jsonplaceholder.networking.NetworkException
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response

/**
 * A block that is executed on successful operations
 */
typealias OnSuccess<T> = (T) -> Unit

/**
 * A block that is executed on failed operations
 */
typealias OnFailure = (NetworkException) -> Unit

/**
 * The result of a deferred network operation.
 */
class DeferredNetworkResult<E> {

    /**
     * The onSuccess block is executed when the operation finishes successfully
     */
    var onSuccess: OnSuccess<E>? = null

    /**
     * The onFailure block is executed when the operation fails
     */
    var onFailure: OnFailure? = null
}


/**
 * The network call extension function launches a deferred network operation in a coroutine.
 */
fun <R: Response<M>, M: Any> Deferred<R>.networkCall(block: DeferredNetworkResult<M>.() -> Unit) {
    val deferredResult = DeferredNetworkResult<M>().apply(block)

    GlobalScope.launch(Dispatchers.Main) {
        lateinit var response: R
        try {
            response = await()
            if (response.isSuccessful) {
                val body = response.body()
                if (body == null) {
                    deferredResult.onFailure?.invoke(NetworkException("Response body is null. $response"))
                } else {
                    deferredResult.onSuccess?.invoke(body)
                }
            } else {
                deferredResult.onFailure?.invoke(NetworkException("Request failed. $response"))
            }
        } catch (exception: Throwable) {
            deferredResult.onFailure?.invoke(NetworkException("Call failed with exception.", exception))
        }
    }
}