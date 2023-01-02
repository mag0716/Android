# [Type safe, multi-module best practices with Navigation Compose](https://www.youtube.com/watch?v=goFpG25uoc8)

* Define your screen as state in, events out
* Split up your navigation graph
  * destinationごとに分割している
  * ViewModelの解決をNavigationの定義内で行なっている
* Build only the public APIs you need
* Module structure -> Graph structure
  * モジュールごとにNavigation Graphを用意しておいて、appモジュールでアプリ全体の遷移を表すNavigation Graphを用意する
* Check out the Updated resources
  * 参考
    * https://developer.android.com/guide/navigation/navigation-type-safety
    * NowInAndroid
