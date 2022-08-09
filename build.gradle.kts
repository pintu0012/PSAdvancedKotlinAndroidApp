// Top-level build file where you can add configuration options common to all sub-projects/modules.

//buildscript {
//    repositories {
//        google()
//    }
//    dependencies {
//        def nav_version = "2.3.4"
//        def hilt_version="2.38.1"
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")
//    }
//}
//
//plugins {
//    id 'com.android.application' version '7.1.2' apply false
//    id 'com.android.library' version '7.1.2' apply false
//    id 'org.jetbrains.kotlin.android' version '1.6.21' apply false
//    id 'org.jetbrains.kotlin.jvm' version '1.6.21' apply false
//}
//
//
//
//
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven (url=  Config.ClassPaths.jitPackUrl )
        maven(url = Config.ClassPaths.pluginGradle)

    }
    dependencies {
        classpath(Config.ClassPaths.androidGradle)
        classpath(Config.ClassPaths.kotlinGradle)
        classpath(Config.ClassPaths.daggerHiltGradle)
        classpath(Config.ClassPaths.navigationSafArgsGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    }
    
}
tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}

allprojects {
    configurations {
        all {
            resolutionStrategy {
                force(  "org.objenesis:objenesis:2.6")
            }
        }
    }
}