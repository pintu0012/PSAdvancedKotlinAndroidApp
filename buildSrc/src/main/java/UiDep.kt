object UiDep {
    // Kotlin
    const val kotlin = AppDependencies.KotlinDep.kotlin

    // Core
    const val coreKtx = AppDependencies.CoreDep.coreKtx
    const val appCompat = AppDependencies.CoreDep.appCompat
    const val material = AppDependencies.CoreDep.material
    const val constraint = AppDependencies.CoreDep.constraint
    const val navigationFragmentKtx = AppDependencies.CoreDep.navigationFragmentKtx
    const val navigationUiKtx = AppDependencies.CoreDep.navigationUiKtx
    const val activityKtx = AppDependencies.CoreDep.activityKtx

    // LifeCycle
    val LifeCycle = listOf(
        AppDependencies.LifeCycleDep.viewModelKtx,
        AppDependencies.LifeCycleDep.lifeCycleExtension,
        AppDependencies.LifeCycleDep.lifeCycleRuntime,
        AppDependencies.LifeCycleDep.lifeCycleRuntimeKtx
    )

    // Hilt
    val DaggerHilt = listOf(
        AppDependencies.DaggerHiltDep.hiltAndroid
    )

    val DaggerHiltKapt = listOf(
        AppDependencies.DaggerHiltDep.hiltAndroidKapt,
        AppDependencies.DaggerHiltDep.hiltKapt
    )

    // Coroutines
    val Coroutines = listOf(
        AppDependencies.CoroutinesDep.coroutineCore,
        AppDependencies.CoroutinesDep.coroutineAndroid,
        AppDependencies.CoroutinesDep.coroutineTest,
    )

    const val glide = AppDependencies.GlideDep.glide
    const val glideKapt = AppDependencies.GlideDep.glideKapt
    const val timber = AppDependencies.TimberDep.timber
    const val lottie = AppDependencies.LottieDep.lottie

    object Test {
        const val truth = AppDependencies.TestDep.truth
        const val mockitoAndroid = AppDependencies.TestDep.mockitoAndroid
        const val junit = AppDependencies.TestDep.junit
        const val coroutines = AppDependencies.TestDep.coroutinesTest
        const val mockitoKotlin = AppDependencies.TestDep.mockitoKotlin
        const val mockitoInline = AppDependencies.TestDep.mockitoInline
        const val mockitoCore = AppDependencies.TestDep.mockitoCore
        const val assertJ = AppDependencies.TestDep.assertJ
        const val androidxArchCore = AppDependencies.TestDep.androidxArchCore
        const val robolectric = AppDependencies.TestDep.robolectric
        const val testExtJunit = AppDependencies.TestDep.testExtJunit
    }

}