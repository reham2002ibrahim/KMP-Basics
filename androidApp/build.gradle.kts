plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.day11kmp.android"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.example.day11kmp.android"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

configurations.all {
    exclude(group = "xpp3", module = "xpp3")
}

dependencies {
    implementation(projects.shared)
    {
        exclude(group = "xpp3", module = "xpp3")
    }
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.safe.args.generator)
    implementation("androidx.navigation:navigation-compose:2.7.7")
    debugImplementation(libs.compose.ui.tooling)
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("io.insert-koin:koin-androidx-compose:3.4.3")
    implementation("io.insert-koin:koin-android:3.4.3")
}