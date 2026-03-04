package com.nexxtap.utilities.testattestsdk

import com.google.android.play.core.integrity.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class FintechIntegrityManager(
    private val params: IntegrityInitParameters
) {

    private val integrityManager =
        IntegrityManagerFactory.create(params.context)

    suspend fun requestAttestation(): Result<PlayIntegrityPackage> {

        return try {

            val nonce = params.nonceProvider.fetchNonce()

            val request = IntegrityTokenRequest.builder()
                .setNonce(nonce)
                .setCloudProjectNumber(params.cloudProjectNumber)
                .build()

            val response = suspendCancellableCoroutine<IntegrityTokenResponse> { cont ->
                integrityManager.requestIntegrityToken(request)
                    .addOnSuccessListener { cont.resume(it) }
                    .addOnFailureListener { cont.resumeWithException(it) }
            }

            val token = response.token()

            Result.success(
                PlayIntegrityPackage(
                    integrityToken = token,
                    nonce = nonce,
                    requestTimestamp = System.currentTimeMillis(),
                    packageName = params.context.packageName
                )
            )

        } catch (e: IntegrityServiceException) {
            Result.failure(IntegrityException.ApiError(e.errorCode))
        } catch (e: Exception) {
            Result.failure(IntegrityException.Unknown(e.message ?: "Unknown error"))
        }
    }

}