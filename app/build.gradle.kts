plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "fr.groggy.racecontrol.tv"

    compileSdk = 34
    
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "fr.groggy.racecontrol.tv"
        minSdk = 21
        targetSdk = 34
        versionCode = 43
        versionName = "2.7.0"

        buildConfigField("String", "DEFAULT_USER_AGENT", "\"RaceControl f1viewer\"")
    }

    signingConfigs {
        create("release") {
            storeFile = project.properties["signing.key.store.path"]?.let { file(it) }
            storePassword = project.properties["signing.key.password"] as String?
            keyAlias = project.properties["signing.key.alias"] as String?
            keyPassword = project.properties["signing.key.password"] as String?
        }
    }

    buildTypes {
        val appName = "F1 TV Player"
        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isDebuggable = true
            resValue("string", "app_name", "$appName (debug)")
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
            resValue("string", "app_name", appName)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    val kotlinCoroutinesVersion = "1.7.3"
    implementation(kotlin("stdlib", "1.9.22"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kotlinCoroutinesVersion")

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.fragment:fragment-ktx:1.7.1")
    val leanbackVersion = "1.2.0-alpha04"
    implementation("androidx.leanback:leanback:$leanbackVersion")
    implementation("androidx.leanback:leanback-preference:$leanbackVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    val hiltVersion = rootProject.extra["hiltVersion"]
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    val okHttpVersion = "4.12.0"
    implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:$okHttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")

    val moshiVersion = "1.15.1"
    implementation("com.squareup.moshi:moshi-kotlin:$moshiVersion") {
        exclude(module = "kotlin-reflect")
    }
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")

    implementation("com.auth0.android:jwtdecode:2.0.2")

    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    implementation("com.github.bumptech.glide:okhttp3-integration:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion")

    val exoplayerVersion = "2.19.1"
    implementation("com.google.android.exoplayer:exoplayer:$exoplayerVersion")
    implementation("com.google.android.exoplayer:extension-leanback:$exoplayerVersion")
    implementation("com.google.android.exoplayer:extension-okhttp:$exoplayerVersion")

    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    implementation("com.google.android.material:material:1.12.0")
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.4")
}

kapt {
    correctErrorTypes = true
}
