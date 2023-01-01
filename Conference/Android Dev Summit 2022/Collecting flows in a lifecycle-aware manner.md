 # [Collecting flows in a lifecycle-aware manner](https://www.youtube.com/watch?v=6hNXFs1fYaY)

 * Data sources -> Repositories -> Use cases -> ViewModel までは Flow
  * ViewModel -> UI で StateFlow に変換されている
* androidx.lifecycle.lifecycle-runtime-ktx
  * `repeatOnLifecycle`
    * Fragment では `onViewCreated` で `repeatOnLifecycle` を呼び出している
    * suspend fun で そのあとのコードはすでにライフサイクルが破棄五となるので他の処理を書いてはだめ
  * `flowWithLifecycle`
    * `viewModel.uiState.flowWithLifecycle`
    * 内部では `repeatOnLifecycle` が使われている
* androidx.lifecycle.lifecycle-runtime-compose
  * `collectAsStateWithLifecycle`
    * `viewModel.uiState.collectAsStateWithLifecycle()`
    * 内部では `repeatOnLifecycle`, `produceState` が使われている
* 参考
  * https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda
  * https://medium.com/androiddevelopers/repeatonlifecycle-api-design-story-8670d1a7d333
  * https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3
