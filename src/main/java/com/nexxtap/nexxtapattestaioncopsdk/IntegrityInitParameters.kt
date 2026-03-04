package com.nexxtap.utilities.testattestsdk

import android.content.Context

data class IntegrityInitParameters(
    val context: Context,
    val cloudProjectNumber: Long,
    val nonceProvider: BackendNonceProvider
)
