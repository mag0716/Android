# [Test at scale with Gradle managed devices](https://www.youtube.com/watch?v=LQFataWfTXI)

* Initialize -> Run your tests -> Shutdown
* systemImageSource
  * Google Play Servicesを利用したい場合はgoogleを指定
* Automated Test Devices(ATds)
  * Lightweight
    * 描画を避けることでCPU,メモリ消費量を減らすがスクリーンショットは可能
  * Optimized
    * 不要なアプリ、Servicesは削除されている
  * Consistent
    * IDEとCIで同じイメージが利用されるため結果も同じとなる
  * systemImageSource
    * aosp-atd, google-atd
* Firebase Test Labと連携させることもできる
  * OEMの確認が必要なケースではこちらで
  * AGP 8.0以上
