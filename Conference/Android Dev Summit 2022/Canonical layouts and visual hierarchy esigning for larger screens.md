# [Canonical layouts and visual hierarchy: Designing for larger screens](https://www.youtube.com/watch?v=FrkIa9vZjCI)

* List-detail：Parent->Child
  * ActivityEmbedding
    * xmlで領域の割合や最大幅を指定できる
  * SlidingPaneLayout
* Supporting Panel：Primary->Secondary
  * ConstraintLayout
  * MotionLayout
  * LinearLayout
  * 一般的にはBodyの領域の方が広い
  * Compose + TwoPane
    * Accompanist
    * SupportingPanelのコード例
* Feed：Peers/Sibilings
  * LazyGridLayout
    * `rememberColumns`
      * 画面サイズによって行数を動的に変えるためのコード例
  * RecyclerView, GridLayoutManager/StaggeredGridLayoutManager
    * リソースファイルに定義した行数を取得して画面サイズによって変える
* Layout Regions
  * Navigation
  * App Bar
  * Body
* bools.xml で isTablet を定義するのは NG
