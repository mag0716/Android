# Koin Sample

## 対象バージョン

* 1.0.1

## サンプル

| module | 概要 | 備考 |
| - | - | - |
| hello | はじめての Koin | Activity のプロパティへ inject |
| constructorinjection | Constructor Injection | ログで `single` と `factory` の生成タイミングの違いを確認 |

## 疑問点

* multi modules でどう利用するのか？
  * 別モジュールにある interface を `module` で使用すると `Unresolved reference` となる
