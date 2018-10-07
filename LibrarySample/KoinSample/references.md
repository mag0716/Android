# https://insert-koin.io/docs/1.0/getting-started/introduction/

## https://insert-koin.io/docs/1.0/getting-started/android/

* `module` でモジュールを定義する
  * `single`：シングルトン
  * `factory`：Inject のたびにインスタンスが毎回生成される
* `Application#onCreate` で `startKoin` を呼び出す
* `by inject()` を使って Inject する
  * インジェクトされるのは、Activity, Fragment, Service などの Android のコンポーネント
