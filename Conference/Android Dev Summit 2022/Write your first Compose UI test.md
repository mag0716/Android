# [Write your first Compose UI test](https://www.youtube.com/watch?v=JyUJZvJ-OV8)

* 電卓アプリが題材
* ui-test-junit4
* ui-test-manifest
* `createComposeRule`
* `onNodeWithText` でテキストが合致する要素を探す
  * おなじテキストが複数存在していると動作しない
  * `onNode` で条件をいくつか指定することで操作したい要素にマッチさせる
    * `hasText`
    * `hasClickAction`
    * 共通のプロパティとして用意しておくとテストコードがシンプルになる
* リソースファイルから文字列を取得するためには rule から `activity` を取得する
  * `createAndroidComposeRule`
  * `rule.activity.getString`
* 色の確認はできない
  * したいなら screenshot テストで
