//plugins {
//    id 'com.android.application'
//    id 'org.jetbrains.kotlin.android'
//    id 'kotlin-kapt'
//    id 'dagger.hilt.android.plugin'
//    id 'androidx.navigation.safeargs.kotlin'
//}
//
//android {
//    compileSdk 32
//
//    defaultConfig {
//        applicationId "com.example.ecommerceapp"
//        minSdk 21
//        targetSdk 32
//        versionCode 1
//        versionName "1.0"
//        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//
//    }
//
//    packagingOptions {
//        exclude 'META-INF/AL2.0'
//        exclude 'META-INF/LGPL2.1'
//        exclude 'META-INF/gradle/incremental.annotation.processors'
//    }
//
//    buildTypes {
//        release {
//            minifyEnabled false
//            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//        }
//    }
//    compileOptions {
//        sourceCompatibility JavaVersion.VERSION_1_8
//        targetCompatibility JavaVersion.VERSION_1_8
//    }
//    kotlinOptions {
//        jvmTarget = '1.8'
//    }
//    buildFeatures {
//        viewBinding true
//        dataBinding true
//    }
////    dataBinding {
////        enabled  true
////    }
//}
//
//dependencies {
//
//    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
//    implementation 'androidx.recyclerview:recyclerview:1.2.1'
//    def lifecycle_version = "2.2.0"
//    def arch_version = "2.1.0"
//    def room_version = "2.4.2"
//    def coroutines_version = "1.3.7"
//    def retrofit_version = "2.9.0"
//    def Interceptor_version = "4.7.2"
//    def Glide_version = "4.11.0"
//    def nav_version = "2.3.4"
//    def hiltVersion = "1.0.0-alpha02"
//    def hiltAndroidVersion = "2.38.1"
//
//    implementation 'androidx.core:core-ktx:1.7.0'
//    implementation 'androidx.appcompat:appcompat:1.4.2'
//    implementation 'com.google.android.material:material:1.6.1'
//    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
//    testImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//
//    // ViewModel
//    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
//    // LiveData
//    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
//    // Saved state module for ViewModel
//    implementation "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"
//    // Annotation processor
//    kapt "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"
//    implementation "androidx.room:room-runtime:$room_version"
//    kapt "androidx.room:room-compiler:$room_version"
//    // Kotlin Extensions and Coroutines support for Room
//    implementation "androidx.room:room-ktx:$room_version"
//    //coroutines
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
//    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
//    //hilt
//    implementation"com.google.dagger:hilt-android:$hiltAndroidVersion"
//    implementation"com.google.dagger:hilt-compiler:$hiltAndroidVersion"
//    implementation"androidx.hilt:hilt-compiler:$hiltVersion"
//    //retrofit
//    implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
//    implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
//    implementation "com.squareup.okhttp3:logging-interceptor:$Interceptor_version"
//    //Glide
//    implementation "com.github.bumptech.glide:glide:$Glide_version"
//    kapt "com.github.bumptech.glide:compiler:$Glide_version"
//    //Navigation
//    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
//    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
//
//    //HILT
////    implementation "com.google.dagger:hilt-android:$hiltAndroidVersion"
////    implementation "com.google.dagger:hilt-compiler:$hiltAndroidVersion"
////    implementation "androidx.hilt:hilt-compiler:$hiltVersion"
//
//    testImplementation 'junit:junit:4.13.2'
//
//    testImplementation 'androidx.test.ext:junit:1.1.3'
//    testImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    testImplementation "androidx.arch.core:core-testing:2.1.0"
//    testImplementation "com.google.truth:truth:1.0.1"
//    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
//    testImplementation "org.robolectric:robolectric:4.5.1"
//
//    androidTestImplementation 'junit:junit:4.13.2'
//    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
//    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
//    androidTestImplementation "androidx.arch.core:core-testing:2.1.0"
//    androidTestImplementation "com.google.truth:truth:1.0.1"
//    androidTestImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
//
//
//}

plugins {
    id(Config.Plugins.android)
    id(Config.Plugins.kotlinAndroid)
    id(Config.Plugins.navigationSafArgs)
    id(Config.Plugins.kotlinKapt)
    id(Config.Plugins.dagger)
    id("org.jetbrains.kotlin.android")
}

android {

        packagingOptions {
        exclude("META-INF/AL2.0")
        exclude ("META-INF/LGPL2.1")
        exclude ("META-INF/gradle/incremental.annotation.processors")
    }

    buildFeatures {

        //just for dataBinding ，It has nothing to do with viewBinding
        dataBinding = true

        //just for viewBinding ，It has nothing to do with dataBinding
        viewBinding = true
    }

    compileSdkVersion(Config.Android.androidCompileSdkVersion)
    defaultConfig {
        applicationId = (Environments.Release.appId)
        minSdk = (Config.Android.androidMinSdkVersion)
        targetSdk = (Config.Android.androidTargetSdkVersion)
        versionCode = (Environments.Release.appVersionCode)
        versionName = (Environments.Release.appVersionName)
        testInstrumentationRunner = (Config.testRunner)
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

    //Modules
//    implementation(project(Modules.cache))

    //std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //Core Dependencies
    implementation(UiDep.kotlin)
    implementation(UiDep.coreKtx)
    implementation(UiDep.appCompat)
    implementation(UiDep.material)
    implementation(UiDep.constraint)
    implementation(UiDep.navigationFragmentKtx)
    implementation(UiDep.navigationUiKtx)
    implementation(UiDep.activityKtx)
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    //Lifecycle
    UiDep.LifeCycle.forEach {
        implementation(it)
    }
    //Dagger-Hilt
    UiDep.DaggerHilt.forEach {
        implementation(it)
    }
    UiDep.DaggerHiltKapt.forEach {
        kapt(it)
    }
    //Coroutines
    UiDep.Coroutines.forEach {
        implementation(it)
    }
    testImplementation(UiDep.Coroutines[2])

    //Glide
    implementation(UiDep.glide)
    kapt(UiDep.glideKapt)
    //Timber
    implementation(UiDep.timber)

    // Test Dependencies
    testImplementation(UiDep.Test.truth)
    testImplementation(UiDep.Test.junit)
    testImplementation(UiDep.Test.assertJ)
    testImplementation(UiDep.Test.mockitoKotlin)
    testImplementation(UiDep.Test.mockitoInline)
    testImplementation(UiDep.Test.mockitoCore)
    testImplementation(UiDep.Test.coroutines)
    testImplementation(UiDep.Test.androidxArchCore)
    testImplementation(UiDep.Test.robolectric)
    testImplementation(UiDep.Test.testExtJunit)
    // Retrofit
    RemoteDep.retrofit.forEach {
        implementation(it)
    }
    //FlexBoxLayout
//    implementation(UiDep.flexBoxLayout)

    //Room
    CacheDep.room.forEach {
        implementation(it)
    }
    androidTestImplementation(UiDep.Test.truth)
    androidTestImplementation(UiDep.Test.mockitoAndroid)
    kapt(CacheDep.roomKapt)

}