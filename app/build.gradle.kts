plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.registration_details"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.registration_details"
        minSdk = 26
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
        dataBinding=true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Navigation Fragment for handling navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.4")

    // Navigation UI for integrating with UI components (e.g., BottomNavigationView)
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.4")
}