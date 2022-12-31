# [More performance tips for Jetpack Compose](https://www.youtube.com/watch?v=ahXLwg2JYpc)

* Macrobenchmark
  * 正の値が結果として出力されていると処理落ちしているということ
* よくある問題
  * Defer reading state
    * Compositionに変化がないのであればスキップするとパフォーマンスがよくなる
    * lambdaを使うことでrecompositionを防ぐ
    * `Prefer lambda modifiers when using frequently changing state`
    * 参考：https://medium.com/androiddevelopers/jetpack-compose-debugging-recomposition-bfcf4a6f8d37
  * Stability
    * Composable Functions
      * Restartable
      * Skippable
    * Parameters
      * Immutable
      * Stable
      * Unstable
    * Compose Compiler Reports
      * <modulename>-classes.txt
      * <modulename>-composables.txt
      * <modulename>-composables.csv
    * List, Set, Map は unstable として扱われる
      * immutableであることを保証されないため
      * 対策
        * KotlinX immutable collections
        * Annotations
      * 全てのListをImmutableにしないといけないのか？
        * ->  No. パフォーマンス問題が発生する箇所のみでOK.
    * external moduleのクラスは unstable として扱われる
      * 対策
        * Annotations
      * 全ての Composable を skippable にしないといけないのか？
        * ->  No. パフォーマンス問題が発生する箇所のみでOK.
      * 参考：https://medium.com/androiddevelopers/jetpack-compose-stability-explained-79c10db270c8
  * derivedStateOf {}
    * `derivedStateOf {} is used when your state or key is changing more than you want to update your UI`
    * distinctUntinChanged と似ている
    * vs `remember(key) {}`
      * State needs to change as much as key changes.
  * reportFullyDrawn
    * システム側にアプリの起動を最適化を許可する
    * activity-compose:1.7.0-alpha01 以上
