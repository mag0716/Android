# memo

## 自分の考えまとめ

* どういう単位でモジュールを分けよう？

## references memo

* モジュールの分け方
  * コンテキスト毎
    * 別のコンテキストのコードが入り込まないようにする
    * これはアプリによって変わるので、そのアプリの担当者が設計する必要がある
      * どうしても担当者のレベル次第になってしまう
  * レイヤー毎
    * 依存の方向性を強制させる
* common モジュール
  * 汎用的な Extension や Utility
* resource モジュール
  * strings や layout 以外のリソース
* ★api モジュール
  * swagger などで自動生成
  * サーバ側の仕様に振り回されなくてすむので、これは導入したい
* レイヤー化アーキテクチャ
  * domain, infra, usecase, ui
    * domain
      * abstract method で定義し、infra で実装する(APIなどが必要な場合)
    * usecase：アプリのためのロジック
      * ログイン処理での validate など
      * 「インプットやアクションを受け取ったら、処理を行い、アウトプットや通知する」で1つの UseCase
      * メソッドは `execute`
    * infra：技術的関心ごと(永続化ロジック、API)
* 依存性逆転の法則
  * 上位モジュールは下位モジュールに依存してはならない。両者は抽象に依存すべき
  * 抽象は具体に依存してはならない。具体は具体に依存すべき
* ★`Identifier` 型
  * 型で制約を作る
  * 導入も簡単だし、効果も高そう
* Repository
  * オブジェクトの永続化、永続されたオブジェクトの検索の窓口
  * domain では interface のみ
  * Rx の利用
    * ここで Rx に依存してしまうのは何とかしたい
* ui モジュールでのアーキテクデャは MVP + DataBinding
  * Contract
    * 確かに、Presenter と View の関係は分かりやすくなる
    * ただ、別画面で共通で利用する Presenter、View がある場合に定義が細かくなりそう？
  * ★ BindingModel
    * AAC の ViewModel と命名が被って分かりにくいので良さそう
  * Presenter
    * 「XXX されたら、YYY する」の「XXX されたら」を定義する
      * 何を定義すべきかが明確なので分かりやすい
      * 個人的には、Presenter に「XXX されたら」よりも、「YYY する」が明確になっていてほしい
        * Presenter が UseCase も含んでしまっている？
    * イベント管理に徹する
      * 画面遷移の場合は、ScreenTransition
      * 適切な UseCase を呼び出し、View に返すだけ
        * BindingModel の変換もやる
    * ScreenTransition
      * Contract 単位
        * ScreenTransition を1つにすると、どの画面から遷移するのかが分かりにくい
          * Navigation 使えば解消できるかも
      * 実装は Activity で
  * ★拡張関数：標準機能がプラットフォームや言語にかけていると感じたときのみ
* ★`internal` で他モジュールから参照できなくする
  * 必須
* デメリット
  * DataBinding と相性が悪いが Data Binding Compiler V2 で解消されてる？
* レイヤー単位でモジュール化すると、認証機能が必要な場合に実装が困難になる
* moduels dir
  * コンテキスト単位 * レイヤー単位で分ける場合は、コンテキスト単位でディレクトリを作成した方がよさそう
* 既存アプリでやる場合
  * モジュールを作成し、全ての機能を移してしまう
* ★依存定義を buildSrc + Kotlin で記述
  * buildSrc ディレクトリにソースを置く
  * モジュールが多数になると定義が面倒なので必須
  * https://handstandsam.com/2018/02/11/kotlin-buildsrc-for-better-gradle-dependency-management/ が参考になりそう
* `core` モジュール
  * Retrofit や SharedPreferences などの共通の依存関係を提供する
  * Utility、拡張関数
  * グローバルクラスやコールバック
  * Firebase, LeakCanary などの初期化

## references

* https://developer.android.com/studio/projects/
* https://github.com/googlesamples/android-instant-apps/tree/master/multi-feature-module
  * Google の Instant Apps のサンプルプロジェクト

### 確認済み

* https://motida-japan.hatenablog.com/entry/2018/02/09/102628
  * https://www.youtube.com/watch?v=s1nd_GrA56s
    * DroidKaigi2018 での発表
  * https://kgmyshin.booth.pm/items/837226
* https://speakerdeck.com/nein37/kutukupatudoapurifalsemarutimoziyuruhua-hefalsequ-rizu-mi
  * クックパッド社の事例
* https://proandroiddev.com/multi-module-android-project-codebase-ffc153cf7a1e
  * Dagger2 を使ったサンプル
    * https://github.com/frogermcs/MultiModuleGithubClient
  * コンテキスト毎に分割
* https://medium.com/mindorks/writing-a-modular-project-on-android-304f3b09cb37
  * https://github.com/karntrehan/Posts
  * モジュールは core と posts(app) の2つのみ
  * Room
    * モジュール毎に DB を持つようにしている
  * Dagger2 を使ったサンプル
* https://medium.freecodecamp.org/how-modularisation-affects-build-time-of-an-android-application-43a984ce9968
  * ビルド時間を減らすことが一番の目的
  * マルチモジュールにすることでどれだけビルド時間が減らせるかを検証している
  * `defaultConfig`

### 未確認

* https://engineeringblog.yelp.com/2018/06/how-yelp-modularized-the-android-app.html
  * Yelp
