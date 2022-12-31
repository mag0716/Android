# [Building a scalable, modularized, testable app from scratch](https://www.youtube.com/watch?v=qX6zmKY4KP0)

* Now in Android 
* data layer
  * data source と repository は分離
    * offline サポートが容易になる
* UI layer
  * State holder
    * AAC の ViewModel を利用することが可能
      * configuration changed にも対応できることが利点
    * 複数のリポジトリから取得したデータを結合しUiStateを生成する
    * Loading, Success を sealed interface を使って表現している
    * `stateIn`
      * `started` に `SharingStarted.WhileSubscribed` を使っている
  * Screen
    * StatefulなComposableとStatelessなComposableを用意している
      * テストとPreviewが楽になるので
* Module
  * :app：Bringing features together
    * Wear OSなどの他のプラットフォームの対応が必要になったら専用のモジュールを用意する
  * :feature：Single user-facing functionality
  * :core：Single area of common functionality
    * :model
    * :data
      * repository
    * :datastore
    * :network
  * KotlinやTestingなど共通な定義のためにConvention pluginsを利用
    * :build-logic
  * バージョン定義にVersion catalogsを利用
* Test
  * `createComposeRule()`
    * `setContent` で Composable 関数を呼び出せるようになる
      * ここで Stateless な Composable 関数を利用するとテストコードがシンプルになる
    * `onNodeWithText`
* Material Design
  * アプリ内に Component Catalog を用意し、確認を容易にしている
