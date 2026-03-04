package com.nexxtap.utilities.testattestsdk

/*
SecRule: nonce must come from backend and never generated locally
*/

interface BackendNonceProvider {
    suspend fun fetchNonce(): String
}