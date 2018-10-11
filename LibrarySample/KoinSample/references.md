# https://insert-koin.io/docs/1.0/getting-started/introduction/

## https://insert-koin.io/docs/1.0/getting-started/android/

* `module` でモジュールを定義する
  * `single`：シングルトン
  * `factory`：Inject のたびにインスタンスが毎回生成される
* `Application#onCreate` で `startKoin` を呼び出す
* `by inject()` を使って Inject する
  * インジェクトされるのは、Activity, Fragment, Service などの Android のコンポーネント

## https://insert-koin.io/docs/1.0/getting-started/android-scope/

* `module` 内で `scope` を利用する
* `bindScope` で　`Lifecycle` に scope を紐づける
  * デフォルトでは `ON_DESTROY` で closing される
  * `Scope` は `getOrCreateScope` で生成、取得するのがよい
* `Scope` を自前で管理すれば、複数 Activity でインスタンスを共有することができる
  * 使い終わったら、`Scope#close()` する

## https://insert-koin.io/docs/1.0/getting-started/android-viewmodel/

* `module` 内で `viewModel` を利用する
* 利用時には `by viewModel()` を指定すれば、Inject され、現在のコンポーネントにバインドしてくれる
  * `ViewModelFactory` にリンクされている
* `getViewModel()` でも取得可能
