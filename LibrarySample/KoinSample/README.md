# Koin Sample

## 対象バージョン

* 1.0.1

## サンプル

| module | 概要 | 備考 |
| - | - | - |
| hello | はじめての Koin | Activity のプロパティへ inject |
| constructorinjection | Constructor Injection | ログで `single` と `factory` の生成タイミングの違いを確認 |

### constructorinjection

```
2018-10-07 22:42:41.007 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: com.github.mag0716.constructorinjection.ProductHelloService@225ecbc
2018-10-07 22:42:41.007 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: hello(product)(com.github.mag0716.constructorinjection.ProductPresenter@daf4445)
2018-10-07 22:42:43.832 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: com.github.mag0716.constructorinjection.ProductHelloService@225ecbc
2018-10-07 22:42:43.832 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: hello(product)(com.github.mag0716.constructorinjection.ProductPresenter@926cfb4)

```

## 疑問点

* multi modules でどう利用するのか？
  * 別モジュールにある interface を `module` で使用すると `Unresolved reference` となる
