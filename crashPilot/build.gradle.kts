plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    namespace = "com.lzcalderaro.crashpilot"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        aarMetadata {
            minCompileSdk = 26
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {

    api("androidx.core:core-ktx:1.12.0")
    api("androidx.appcompat:appcompat:1.6.1")
    api("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    api("com.squareup.okhttp3:okhttp:4.12.0")
    api("com.fasterxml.jackson.core:jackson-core:2.13.0")
    api("com.fasterxml.jackson.core:jackson-databind:2.13.0")
}

afterEvaluate {
    publishing {
        publications {
            register<MavenPublication>("crashPilot") {
                groupId = "com.github.lzcalderaro"
                artifactId = "crashPilot"
                version = "1.0"

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
}