package com.nexxtap.utilities.testattestsdk

data class PlayIntegrityPackage(
    val integrityToken: String,   // JWS token from Google
    val nonce: String,            // Backend-issued nonce
    val requestTimestamp: Long,   // Local timestamp
    val packageName: String       // Host app package
)
