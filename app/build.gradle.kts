plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.fortniteapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fortniteapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"${project.properties["BASE_URL"]}\"")

        buildTypes {
            debug {
                buildConfigField("String", "API_KEY", "\"${System.getenv("api.key")}\"")
            }
            release {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                buildConfigField("String", "API_KEY", "\"${System.getenv("api.key")}\"")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)

    // Material Design
    implementation(libs.androidx.navigation.ui.ktx)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Network
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Hilt для работы с ViewModel
    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.process)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}