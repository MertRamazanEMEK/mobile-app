plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.onur.fastproudsearch"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.onur.fastproudsearch"
        minSdk = 31
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
    packagingOptions {
        resources {
            excludes += setOf("META-INF/DEPENDENCIES", "mozilla/public-suffix-list.txt")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildToolsVersion = "35.0.0"
}

dependencies {

    implementation("com.google.firebase:firebase-auth:23.0.0")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.airbnb.android:lottie:6.4.1")
    implementation("androidx.activity:activity:1.9.0")
    implementation("com.google.firebase:firebase-crashlytics-buildtools:3.0.2")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.2.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.squareup.picasso:picasso:2.71828")


}