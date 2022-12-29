# [Compose: Implementing responsive UI for larger screens](https://www.youtube.com/watch?v=fQjDtCtri4s)

* アプリ起動中に画面サイズが変化することがある点を考慮しておく
* calculateWindowSizeClass
  * material3-window-size-class
* Canonical layouts
  * https://github.com/android/user-interface-samples/tree/main/CanonicalLayouts
  * Feed
    * 関連する項目を一覧で表示する
    * LazyVerticalGrid, LazyVerticalStaggeredGrid  
  * Supporting panel
    * メインコンテンツと追加情報を表示する
    * 例：動画プレイヤーでの関連動画表示
    * calculateDisplayFeatures
      * Foldableの開閉状態、ヒンジ
    * TwoPane
      * first, second で Composable を指定できるようにし、strategy でレイアウト方法を切り替えられる
  * List-detail
    * 親子関係の表示
    * TwoPaneが利用できるが、BackHandlerも合わせて使う必要がある
* Component
  * FlowRow
  * Modifier.clickable
    * focus, hover などサポート済み
