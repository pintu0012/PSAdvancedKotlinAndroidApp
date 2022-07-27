object RemoteDep {
    const val kotlin = AppDependencies.KotlinDep.kotlin
    const val javax = AppDependencies.JavaDep.javax
    val retrofit = listOf(
        AppDependencies.RetrofitDep.retrofit,
        AppDependencies.RetrofitDep.gsoniConverter,
        AppDependencies.RetrofitDep.loggingInterceptor,
    )
    const val coroutineCore = AppDependencies.CoroutinesDep.coroutineCore

    object Test {
        const val junit = AppDependencies.TestDep.junit
        const val coroutines = AppDependencies.TestDep.coroutinesTest
        const val mockitoKotlin = AppDependencies.TestDep.mockitoKotlin
        const val mockitoInline = AppDependencies.TestDep.mockitoInline
        const val assertJ = AppDependencies.TestDep.assertJ
    }
}