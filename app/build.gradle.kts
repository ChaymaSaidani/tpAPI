plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.tpapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.tpapi"
        minSdk = 29
        targetSdk = 33
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {



    implementation ("androidx.appcompat:appcompat:1.4.0")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("com.google.android.material:material:1.7.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
//Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.7.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.7.2")

//Coroutine
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.2.1")
}