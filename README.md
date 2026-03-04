# TestAttestSDK

[![License: GPL
v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

`TestAttestSDK` is an Android library that enables device attestation
checks directly from your application.\
It currently supports Google Play Integrity and is designed to support
additional attestation providers in future releases.

------------------------------------------------------------------------

## 📦 Maven Coordinates

``` kotlin
implementation("com.nexxtap.utility:testattestsdk:<version>")
```

Replace `<version>` with the latest available version on Maven Central.

------------------------------------------------------------------------

## ✨ Features

-   Google Play Integrity API integration
-   Backend nonce support
-   Coroutine-based asynchronous API
-   Structured success and failure handling
-   Designed for secure server-side verification workflows

More attestation providers will be introduced in upcoming releases.

------------------------------------------------------------------------

## 🛠 Requirements

-   Minimum Android API Level: (specify your `minSdk`)
-   Kotlin support
-   Google Play Services with Play Integrity API enabled
-   Backend service for nonce generation

------------------------------------------------------------------------

## 🚀 Getting Started

### 1️⃣ Initialize the SDK

Initialize once during application startup (e.g., in your `Application`
class):

``` kotlin
private fun initializeIntegritySdk() {
    FintechIntegritySdk.initialize(
        IntegrityInitParameters(
            context = applicationContext,
            cloudProjectNumber = 1234567890123L, // Your Google Play Cloud Project Number
            nonceProvider = DemoNonceProvider()
        )
    )
}
```

------------------------------------------------------------------------

### 2️⃣ Implement a Backend Nonce Provider

The SDK requires a backend-issued nonce for security.

``` kotlin
interface BackendNonceProvider {
    suspend fun fetchNonce(): String
}
```

Your implementation should securely fetch a short-lived nonce from your
backend service.

------------------------------------------------------------------------

### 3️⃣ Request a Play Integrity Attestation

``` kotlin
val result = FintechIntegritySdk.getPlayIntegrityPackage()
```

The returned result provides:

-   `onSuccess`
-   `onFailure`

------------------------------------------------------------------------

## ✅ Success Response

On success, a `PlayIntegrityPackage` object is returned:

``` kotlin
data class PlayIntegrityPackage(
    val integrityToken: String,   // JWS token issued by Google
    val nonce: String,            // Backend-issued nonce
    val requestTimestamp: Long,   // Local request timestamp
    val packageName: String       // Host application package name
)
```

The `integrityToken` must be verified server-side.

------------------------------------------------------------------------

## 🔐 Security Recommendations

-   Always validate the integrity token on your backend.\
-   Do not rely solely on client-side verification.\
-   Ensure nonce generation is cryptographically secure and
    short-lived.\
-   Enable and configure Play Integrity in the Google Play Console.

------------------------------------------------------------------------

## 📄 License

This project is licensed under the **GNU General Public License v3.0**.

See the `LICENSE` file included in this repository for full terms.

------------------------------------------------------------------------

## 👤 Maintainer

NexxTap Utilities

------------------------------------------------------------------------

## 📬 Support

For issues, feature requests, or contributions, please use the project's
issue tracker.
