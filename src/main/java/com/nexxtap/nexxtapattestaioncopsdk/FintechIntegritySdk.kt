package com.nexxtap.utilities.testattestsdk

object FintechIntegritySdk {

    @Volatile
    private var manager: FintechIntegrityManager? = null

    fun initialize(params: IntegrityInitParameters) {
        manager = FintechIntegrityManager(params)
    }

    suspend fun getPlayIntegrityPackage(): Result<PlayIntegrityPackage> {
        return manager?.requestAttestation()
            ?: Result.failure(IntegrityException.NotInitialized())
    }
}