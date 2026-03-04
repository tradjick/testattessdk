package com.nexxtap.utilities.testattestsdk

sealed class IntegrityException(message: String) : Exception(message) {

    class ApiError(val errorCode: Int) :
        IntegrityException("Play Integrity API error: $errorCode")

    class NotInitialized :
        IntegrityException("SDK not initialized")

    class Unknown(message: String) :
        IntegrityException(message)
}