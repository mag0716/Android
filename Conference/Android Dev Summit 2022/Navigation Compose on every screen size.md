# [Navigation Compose on every screen size](https://www.youtube.com/watch?v=LTLQhC6VadI)

* NavHost について
  * 遷移先の画面を表示するためのコンテナ
    * Dialog や BottomSheet など Floating Windows は別のコンテナで管理される
  * 一般的にNavHost 外で管理するもの
    * TopAppBar
    * BottomNavigation
    * NavigationRail
    * DrawerLayout
  * movableContentOf()
    * lambda を共有することができる
    * NavHost の取得に利用することで複数の画面毎のレイアウトで共有できるようになる
      * 例：BotttomBarLayout と　 NavigatioRailLayout
  * List-Detail
    * ListAndDetail のような Composable を用意する
    * Navigation で画面遷移させるのではなくて選択状態で表示を切り替える
      * バックキーの扱いが必要な場合はBackHandlerを利用
      * Detailに表示するコンテンツが複数種類ある場合はdeep linkで遷移できるようにする
        * ItemDetail に NavHost に持たせる
