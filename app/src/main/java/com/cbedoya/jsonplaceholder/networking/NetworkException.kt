package com.cbedoya.jsonplaceholder.networking

class NetworkException: Throwable {
    constructor(message: String) : super(message, null)

    constructor(cause: Throwable) : super(cause.toString(), cause)

    constructor(message: String, cause: Throwable) : super(message, cause)
}