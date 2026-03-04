plugins {
    id("com.android.library") version "8.4.2"
    id("org.jetbrains.kotlin.android") version "1.9.22"
    id("maven-publish")
    id("signing")
}

group = "com.nexxtap.utilities"
version = "1.0.0"

android {
    namespace = "com.nexxtap.utilities.testattestsdk"

    compileSdk = 34

    defaultConfig {
        minSdk = 30
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("com.google.android.play:integrity:1.3.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])

                groupId = "com.nexxtap.utilities"
                artifactId = "testattestsdk"
                version = "1.0.0"

                pom {
                    name.set("Test Attestation SDK")
                    description.set("Play Integrity wrapper SDK for device attestation.")
                    url.set("https://github.com/nexxtap/testattestsdk")

                    licenses {
                        license {
                            name.set("Apache License 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0")
                        }
                    }

                    developers {
                        developer {
                            id.set("nexxtap")
                            name.set("NexxTap")
                            email.set("dev@nexxtap.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:https://github.com/nexxtap/testattestsdk.git")
                        developerConnection.set("scm:git:ssh://git@github.com:nexxtap/testattestsdk.git")
                        url.set("https://github.com/nexxtap/testattestsdk")
                    }
                }
            }
        }
    }
}

signing {
    sign(publishing.publications)
}