# [Compose Modifiers deep dive](https://www.youtube.com/watch?v=BjGX2RftXsU)

* 1.3で Modifier Nodeがリファクタリングされている
* Modifier Design Goals
  * Composableにクリックやpaddingなど必要になるであろう要素全てを定義されているのは複雑で使いにくい
  * かといって、アプリ側に全て任せてしまうとアプリ側のコードでネストが深くなる
  * これらの問題を解決するために Modifier が用意された
  * ただ、Composable の引数として Modifier を渡す形にするとプロパティとして定義されて複数から利用されてしまって面倒になる
    * クリック時の ripple effect など
  * -> `Modifier.composed` で Modifier を複製を容易にすることで対策
  * `Modifier.clickable` など `Modifier` を戻り値として返す Composable 関数はスキップが不可能なためパフォーマンスに問題を抱えている
    * `Modifier.composed` の結果が比較できないためコンパイラにキャッシュもされない
    * `remember` が必須
* Modifiers In Practice
* Modifier.Node
  * Modifier に abstract class の Node が追加された
  * clickable などは ComposedModifier にはならず Modifier.Element となる
  * padding, background, fillMaxSize, clickable は create() を通じて、xxxNode が割り当てられる
    * State は xxxNode が保持する
  * recompose が発生すると、変化のあった Node だけ update() を通じて更新される
    * 以前のように再生成されない
* Benefits
  * 再コンストラクタがなくなることで Allocations が減る
  * Composition 自体が減る
    * materialize の呼び出しがなくなり、state の保持やスナップショットの保持などが不要になる
  * ツリーが小さくなる
    * clickable も single node になる(以前は16)
* Takeaways
  * パフォーマンスが向上
  * Custom Modifiers では Modifier.Node の考慮が必要になる
  * 後方互換性
    * コードの変更は不要でほとんどの Modifier は Modifier.Node に移行される
