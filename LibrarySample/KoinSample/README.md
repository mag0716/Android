# Koin Sample

## 対象バージョン

* 1.0.1

## サンプル

| module | 概要 | 備考 |
| - | - | - |
| hello | はじめての Koin | Activity のプロパティへ inject |
| constructorinjection | Constructor Injection | ログで `single` と `factory` の生成タイミングの違いを確認 |
| scope | `scope` の利用 | |
| viewmodel | `viewmodel` の利用 | |

### constructorinjection

```
2018-10-07 22:42:41.007 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: com.github.mag0716.constructorinjection.ProductHelloService@225ecbc
2018-10-07 22:42:41.007 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: hello(product)(com.github.mag0716.constructorinjection.ProductPresenter@daf4445)
2018-10-07 22:42:43.832 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: com.github.mag0716.constructorinjection.ProductHelloService@225ecbc
2018-10-07 22:42:43.832 7975-7975/com.github.mag0716.constructorinjection D/ConstructorInjection: hello(product)(com.github.mag0716.constructorinjection.ProductPresenter@926cfb4)

```

### scope

#### BG -> FG

* BG に遷移するだけでは、closing されない
  * `bindScope` でのデフォルト引数が `Lifecycle.Event.ON_DESTROY` だから

```
2018-10-10 23:05:28.716 4505-4505/? I/KOIN: [module] declare Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']
2018-10-10 23:05:28.807 4505-4505/? I/KOIN: [Scope] create Scope
2018-10-10 23:05:28.809 4505-4505/? D/KOIN: |-- [Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']]
2018-10-10 23:05:28.811 4505-4505/? D/KOIN: [Scope] get 'com.github.mag0716.scope.ProductPresenter@6906624' from Scope['Scope']
2018-10-10 23:05:28.812 4505-4505/? D/Scope: com.github.mag0716.scope.ProductHelloService@936feb7
2018-10-10 23:05:28.812 4505-4505/? D/Scope: hello(product)(com.github.mag0716.scope.ProductPresenter@6906624)
```
#### Activity 遷移

* 同じ Scope のインスタンスを利用するので遷移先の Activity でも同じインスタンスが利用される

```
2018-10-10 23:29:07.027 5991-5991/com.github.mag0716.scope I/KOIN: [Scope] create Scope
2018-10-10 23:29:07.028 5991-5991/com.github.mag0716.scope D/KOIN: |-- [Scope [name='Presenter',class='com.github.mag0716.scope.Presenter']]
2018-10-10 23:29:07.029 5991-5991/com.github.mag0716.scope D/KOIN: [Scope] get 'com.github.mag0716.scope.Presenter@9042f67' from Scope['Scope']
2018-10-10 23:29:07.029 5991-5991/com.github.mag0716.scope D/Scope: com.github.mag0716.scope.ProductHelloService@936feb7
2018-10-10 23:29:07.029 5991-5991/com.github.mag0716.scope D/Scope: hello(product)(com.github.mag0716.scope.Presenter@9042f67)
2018-10-10 23:29:10.337 5991-5991/com.github.mag0716.scope D/KOIN: |-- [Scope [name='Presenter',class='com.github.mag0716.scope.Presenter']]
2018-10-10 23:29:10.337 5991-5991/com.github.mag0716.scope D/KOIN: [Scope] get 'com.github.mag0716.scope.Presenter@9042f67' from Scope['Scope']
2018-10-10 23:29:10.337 5991-5991/com.github.mag0716.scope D/Scope: com.github.mag0716.scope.ProductHelloService@936feb7
2018-10-10 23:29:10.337 5991-5991/com.github.mag0716.scope D/Scope: hello(product)(com.github.mag0716.scope.Presenter@9042f67)
```

#### 画面回転

* 画面回転時に scope が closing されている
* 画面回転後に inject されたインスタンスは別物
  * `factory` と同様

```
2018-10-10 22:58:46.700 5869-5869/com.github.mag0716.scope I/KOIN: [Scope] create Scope
2018-10-10 22:58:46.700 5869-5869/com.github.mag0716.scope D/KOIN: |-- [Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']]
2018-10-10 22:58:46.700 5869-5869/com.github.mag0716.scope D/KOIN: [Scope] get 'com.github.mag0716.scope.ProductPresenter@2c54c02' from Scope['Scope']
2018-10-10 22:58:46.700 5869-5869/com.github.mag0716.scope D/Scope: com.github.mag0716.scope.ProductHelloService@118ea9c
2018-10-10 22:58:46.700 5869-5869/com.github.mag0716.scope D/Scope: hello(product)(com.github.mag0716.scope.ProductPresenter@2c54c02)
2018-10-10 22:58:51.611 5869-5869/com.github.mag0716.scope I/KOIN: [Scope] closing 'Scope'
2018-10-10 22:58:51.611 5869-5869/com.github.mag0716.scope D/KOIN: release Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']
2018-10-10 22:58:51.674 5869-5869/com.github.mag0716.scope I/KOIN: [Scope] create Scope
2018-10-10 22:58:51.674 5869-5869/com.github.mag0716.scope D/KOIN: |-- [Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']]
2018-10-10 22:58:51.675 5869-5869/com.github.mag0716.scope D/KOIN: [Scope] get 'com.github.mag0716.scope.ProductPresenter@97cbb0d' from Scope['Scope']
2018-10-10 22:58:51.675 5869-5869/com.github.mag0716.scope D/Scope: com.github.mag0716.scope.ProductHelloService@118ea9c
2018-10-10 22:58:51.675 5869-5869/com.github.mag0716.scope D/Scope: hello(product)(com.github.mag0716.scope.ProductPresenter@97cbb0d)
```

#### Activity 破棄

* BG に遷移して、Activity が破棄されたら、scope も closing されている
* 復帰後に inject されたインスタンスは別物
  * `factory` と同様

```
2018-10-10 23:02:51.193 5212-5212/? I/KOIN: [module] declare Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']
2018-10-10 23:02:51.371 5212-5212/? I/KOIN: [Scope] create Scope
2018-10-10 23:02:51.375 5212-5212/? D/KOIN: |-- [Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']]
2018-10-10 23:02:51.378 5212-5212/? D/KOIN: [Scope] get 'com.github.mag0716.scope.ProductPresenter@acbfc91' from Scope['Scope']
2018-10-10 23:02:51.378 5212-5212/? D/Scope: com.github.mag0716.scope.ProductHelloService@29466b8
2018-10-10 23:02:51.378 5212-5212/? D/Scope: hello(product)(com.github.mag0716.scope.ProductPresenter@acbfc91)
2018-10-10 23:02:59.661 5212-5212/com.github.mag0716.scope I/KOIN: [Scope] closing 'Scope'
2018-10-10 23:02:59.662 5212-5212/com.github.mag0716.scope D/KOIN: release Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']
2018-10-10 23:03:08.215 5212-5212/com.github.mag0716.scope I/KOIN: [Scope] create Scope
2018-10-10 23:03:08.216 5212-5212/com.github.mag0716.scope D/KOIN: |-- [Scope [name='ProductPresenter',class='com.github.mag0716.scope.ProductPresenter']]
2018-10-10 23:03:08.218 5212-5212/com.github.mag0716.scope D/KOIN: [Scope] get 'com.github.mag0716.scope.ProductPresenter@114c2a8' from Scope['Scope']
2018-10-10 23:03:08.218 5212-5212/com.github.mag0716.scope D/Scope: com.github.mag0716.scope.ProductHelloService@29466b8
2018-10-10 23:03:08.218 5212-5212/com.github.mag0716.scope D/Scope: hello(product)(com.github.mag0716.scope.ProductPresenter@114c2a8)
```

### viewmodel

#### アプリ起動

```
2018-10-11 21:03:19.360 4132-4132/com.github.mag0716.viewmodel I/KOIN: [module] declare Factory [name='MainViewModel',class='com.github.mag0716.viewmodel.MainViewModel', binds~(androidx.lifecycle.ViewModel)]
2018-10-11 21:03:19.671 4132-4132/com.github.mag0716.viewmodel I/KOIN: [ViewModel] ~ 'class com.github.mag0716.viewmodel.MainViewModel (Kotlin reflection is not available)'(name:'null' key:'null') - com.github.mag0716.viewmodel.MainActivity@2ffc7d6
2018-10-11 21:03:19.674 4132-4132/com.github.mag0716.viewmodel I/KOIN: +-- 'com.github.mag0716.viewmodel.MainViewModel'
2018-10-11 21:03:19.674 4132-4132/com.github.mag0716.viewmodel D/KOIN: |-- [Factory [name='MainViewModel',class='com.github.mag0716.viewmodel.MainViewModel', binds~(androidx.lifecycle.ViewModel)]]
2018-10-11 21:03:19.683 4132-4132/com.github.mag0716.viewmodel D/KOIN: |-- com.github.mag0716.viewmodel.MainViewModel@4ee9112
2018-10-11 21:03:19.683 4132-4132/com.github.mag0716.viewmodel D/KOIN: !-- [com.github.mag0716.viewmodel.MainViewModel] resolved in 9.267458 ms
2018-10-11 21:03:19.684 4132-4132/com.github.mag0716.viewmodel D/KOIN: [ViewModel] instance ~ com.github.mag0716.viewmodel.MainViewModel@4ee9112
2018-10-11 21:03:19.685 4132-4132/com.github.mag0716.viewmodel D/ViewModel: hello(product)(com.github.mag0716.viewmodel.MainViewModel@4ee9112)
```

#### 画面回転

* `ViewModel` なので画面回転しても同じインスタンス

```
2018-10-11 21:05:08.712 5418-5418/com.github.mag0716.viewmodel I/KOIN: [module] declare Factory [name='MainViewModel',class='com.github.mag0716.viewmodel.MainViewModel', binds~(androidx.lifecycle.ViewModel)]
2018-10-11 21:05:09.054 5418-5418/com.github.mag0716.viewmodel I/KOIN: [ViewModel] ~ 'class com.github.mag0716.viewmodel.MainViewModel (Kotlin reflection is not available)'(name:'null' key:'null') - com.github.mag0716.viewmodel.MainActivity@2ffc7d6
2018-10-11 21:05:09.055 5418-5418/com.github.mag0716.viewmodel I/KOIN: +-- 'com.github.mag0716.viewmodel.MainViewModel'
2018-10-11 21:05:09.056 5418-5418/com.github.mag0716.viewmodel D/KOIN: |-- [Factory [name='MainViewModel',class='com.github.mag0716.viewmodel.MainViewModel', binds~(androidx.lifecycle.ViewModel)]]
2018-10-11 21:05:09.058 5418-5418/com.github.mag0716.viewmodel D/KOIN: |-- com.github.mag0716.viewmodel.MainViewModel@4ee9112
2018-10-11 21:05:09.059 5418-5418/com.github.mag0716.viewmodel D/KOIN: !-- [com.github.mag0716.viewmodel.MainViewModel] resolved in 3.506463 ms
2018-10-11 21:05:09.060 5418-5418/com.github.mag0716.viewmodel D/KOIN: [ViewModel] instance ~ com.github.mag0716.viewmodel.MainViewModel@4ee9112
2018-10-11 21:05:09.061 5418-5418/com.github.mag0716.viewmodel D/ViewModel: hello(product)(com.github.mag0716.viewmodel.MainViewModel@4ee9112)
2018-10-11 21:05:25.240 5418-5418/com.github.mag0716.viewmodel I/KOIN: [ViewModel] ~ 'class com.github.mag0716.viewmodel.MainViewModel (Kotlin reflection is not available)'(name:'null' key:'null') - com.github.mag0716.viewmodel.MainActivity@9c2b5f8
2018-10-11 21:05:25.240 5418-5418/com.github.mag0716.viewmodel D/KOIN: [ViewModel] instance ~ com.github.mag0716.viewmodel.MainViewModel@4ee9112
2018-10-11 21:05:25.240 5418-5418/com.github.mag0716.viewmodel D/ViewModel: hello(product)(com.github.mag0716.viewmodel.MainViewModel@4ee9112)
```

## 疑問点

* multi modules でどう利用するのか？
  * 別モジュールにある interface を `module` で使用すると `Unresolved reference` となる
* `startKoin` で指定する引数
  * `extraProperties`
  * `loadProperties`
