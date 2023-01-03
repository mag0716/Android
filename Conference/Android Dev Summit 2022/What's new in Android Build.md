# [What's new in Android Build](https://www.youtube.com/watch?v=WZ1A7aoEHSw)

* Android Gradle Plugin 8.0での新機能について
  * DSL / Variant API stable
  * Support for test fixtures
  * Incremental Kotlin support
  * Configuration Cache
* For App developers
  * Namespace is mandatory in build.gradle
    * BuildConfigとアプリケーションIDの両方で使われていたものが分離される
      * namespace：R, BuildConfig
      * applicatinId：マニフェストにマージされる
    * AGP 7.3から有効でAGP 8.0.0-alha03から強制
  * BuildConfig is off by default
    * 全てのモジュールで生成されていて不要なファイルが多く生成されていた
      * JavaとKotlinが混ざることでビルドスピードにも悪影響があった
    * android -> buildFeatures -> buildConfig
  * Renderscript and AIDL off by default
    * android -> buildFeatures
      * aidl
      * renderScript
  * R classes are non transitive by default
    * R には該当のモジュールで定義されたもののみ含まれるように変更されることでサイズが減る
    * Android Studio で migrate 可能
    * Android Studio Bumble Bee(AGP 7.1)から新規プロジェクトではtrue
    * AGP 8.0からは指定されていなかったらtrueとなる
  * R8 in full mode by default
  * Android StudioにBuild Analyzerが追加された
    * Plugin, ライブラリのfetchにどれだけ時間がかかっているかも確認できる
      * Failed Requests
      * 取得先の指定順で改善可能
    * Dynamic Versions
* For Plugin developers
  * 11:42からはスキップ
