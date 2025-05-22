kotlin
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Inyectamos Javapoet 1.13 en el classpath del plugin de Hilt
        classpath("com.squareup:javapoet:1.13.0")
    }
}

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android") version "2.50"
    kotlin("plugin.serialization") version "1.9.23"
}

android {
    namespace = "edu.upc.coursemanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.upc.coursemanager"
        minSdk = 26
        targetSdk = 35
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

    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        // Usando catálogo de versiones para Compose Compiler
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }
}

dependencies {
    // AndroidX & Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-compiler:2.50") {
        // Excluimos Javapoet antiguo que trae hilt-compiler
        exclude(group = "com.squareup", module = "javapoet")
    }
    // Inyectamos Javapoet ≥1.13 en el classpath de KAPT
    kapt("com.squareup:javapoet:1.13.0")

    // Retrofit + OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    // Hilt + Compose integration
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
}

// Aseguramos Javapoet ≥1.13 en todas las configuraciones
configurations.all {
    resolutionStrategy.eachDependency {
        if (requested.group == "com.squareup" && requested.name == "javapoet") {
            useVersion("1.13.0")
            because("Hilt-compiler 2.50 necesita ClassName.canonicalName() (Javapoet ≥1.13)")
        }
    }
}
