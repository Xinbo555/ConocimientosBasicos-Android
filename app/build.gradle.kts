plugins {
    alias(libs.plugins.android.application)
    // Hilt requiere el plugin de Google para generar código
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.conocimientosbasicos_andorid_studio"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.conocimientosbasicos_andorid_studio"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    annotationProcessor(libs.hilt.compiler)

    // Room
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // Para el ejercicio de cifrado de BBDD (SQLCipher)
    implementation(libs.android.database.sqlcipher)
    implementation(libs.sqlite)

    // Retrofit y Gson (para convertir JSON a POJOs de Java)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // SharedPreferences Cifradas
    implementation(libs.security.crypto)
}