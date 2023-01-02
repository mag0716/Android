# [Where to hoist that state in Compose?](https://www.youtube.com/watch?v=hWwZ_AuSGfo)

* チャットアプリを題材
* UI logic
  * タップで拡大・縮小を変更させる：Not hoisting
  * JumpToBottom：hoisting
    * scrollStateを親のComposableに渡す必要がある
  * どこにstateをおくべきか？
    * 一番近い共通の先祖
  * Composables
  * Plain state holder class
* Business logic
  * メンション先の入力補完
  * Screen level state holder(AAC ViewModel)
* `Keep state as low as possible`
