plugins {
    id("com.android.application")
}

android {
    namespace = "com.topitup.retailer"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.topitup.retailer"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("androidx.activity:activity:1.9.3")
    implementation("androidx.constraintlayout:constraintlayout:2.2.1")
    implementation("com.google.android.material:material:1.13.0")
    implementation("androidx.fragment:fragment:1.8.5")
    implementation("androidx.navigation:navigation-fragment:2.8.5")
    implementation("androidx.navigation:navigation-ui:2.8.5")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
}
