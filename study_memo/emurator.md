## エミュレータの日本語入力を可能にする

### [手順URL](http://qiita.com/Sam/items/cf97b0eebccb37b3f0fc)
---
#### 詰まった点　　

* 環境変数の設定
	1. export ANDROID_HOME=C:\Users\***\AppData\Local\Android\sdk
	2. adbコマンドの設定

* build-toolsのバージョン
	1. 現在のバージョンを最新にする(25.0.0)
	2. ADBKeyBoard/buid.gradleのバージョン指定を1のものに修正

* ./gradlew installDebugを実行する時
	エミュレータを必ず起動した状態で行う

* adbコマンドの設定  
[参考URL](http://l05d.blogspot.jp/2014/04/windowsandroid-sdk.html)
