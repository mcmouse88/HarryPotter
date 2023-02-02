package com.mcmouse88.harrypotter.utils

import com.google.gson.JsonParseException
import com.mcmouse88.harrypotter.utils.AppExceptions.*
import okio.IOException
import retrofit2.HttpException

sealed class AppExceptions : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(cause: Throwable) : super(cause)

    class ConnectionException(cause: Throwable) : AppExceptions(cause)

    class BackendException(
        val code: Int,
        message: String?
    ) : AppExceptions(message)

    class ParseResponseException(cause: Throwable) : AppExceptions(cause)

    class UnknownException(cause: Throwable) : AppExceptions(cause)

    class EmptyBodyException : AppExceptions()
}

suspend fun<T> wrapAppException(block: suspend () -> T): T {
    try {
        return block.invoke()
    } catch (e: EmptyBodyException) {
        throw e
    } catch (e: JsonParseException) {
        throw ParseResponseException(e)
    } catch (e: HttpException) {
        throw BackendException(e.code(), e.message)
    } catch (e: IOException) {
        throw ConnectionException(e)
    } catch (e: Exception) {
        throw UnknownException(e)
    }
}


