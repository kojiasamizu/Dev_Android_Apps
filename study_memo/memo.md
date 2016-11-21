# ドットインストール簡易メモ  

## 修飾子  
	* public  
		クラス外からのアクセス許可
	* private  
		クラス外からのアクセス禁止
	* static  
		インスタンスを生成せずに、アクセス可能
	* abstract
		[参考](http://java-reference.sakuraweb.com/java_basic_abstract.html)

## エラーの表示
	1. setError(str);  
	2. Toast.makeText(conText , str , time);   
	3. Dialog  
		3-1. Builderの作成  
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

		3-2. Dialogの作成  
		AlertDialog alertDialog = alertDialogBuilder.create();  
		alertDialog.show();

## 画面遷移
	1. Intentの作成  
		Intent intent = new Intent(this , MyResult.class);

	2. 送りたい値を設定
		intent.putExtra(KEY , VALUE); //キーと値のペアで送る(キーは定数化しておくのが推奨)  

	3. 遷移開始  
		startActivity(intent);

	4. 送られてきた値の取得  
		Intent intent = getIntent();
		String myName = intent.getStringExtra(MyForm.EXTRA_MYNAME) //送られてきた文字列を取得

	※元の画面に戻りたい時
	* Manifest.xmlに親アクティビティを記載する
	* finish();  

## 時間処理
**※時間を格納する変数の型は[long]にする!**

	* OSが起動してからの経過時間  
		SystemClock.elapsedRealTime();

	* フォーマット
		SimpleDateFormat sdf = new SimpleDateFormat(型 , ロケール);
		sdf.format(value);

## 割り込み事象
**Handlar ⇛ Runnable(処理) ⇛ UI**

	1. 処理をRunnableというオブジェクトにまとめる  

	2. Handlarを使って投げる
		handlar.postDelayed(Runnable , sec);	//secミリ秒後に実行

	3. ループさせる場合には、Runnableの中に、postDelayedを記載するが、処理中のものがバッティングしないように、先にクリアしてから呼び出す  
		handler.removeCallbacks(updateTimer);
		handler.postDelayed(updateTimer , 10);

## インターネットへの接続
**androidManifestに下記を追加**
<uses-permission android:name="android.permission.INTERNET"/>

## ブラウザ
**ブラウザの設定**
	* myWebView.setWebViewClient(new WebViewClient());
		⇛固有設定のために、WebViewClientのメソッドをoverrideできる  
		myWebView.setWebViewClient(new WebViewClient() {
			@override
			...
			});

## 戻るボタンの動作
	@Override  
	public void onBackPressed() {  
		//履歴の有無を確認  
		if(myWebView.canGoBack()) {  
			myWebView.goBack();  
		}  
	}

## webviewのメモリ使用に関して
**終了時の処理を記載する**
	@Override
	protected void onDestroy() {
		...
	}

## URLの文字列チェック
**入力されたURLの妥当性チェック**
	1. Patterns.WEB_URL.matcher(url).matches()

## 外部テキストファイル
**プログラム内で使用するテキスト群**  
	app/assets/...  
	に格納するのが一般的

**読み込み手順**  
InputStream inputStream = null;  
BufferedReader bufferedReader = null;  

try {  
	inputStream = getAssets().open("quiz.txt");  
	bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  

	//文字列格納変数  
	String s;  
	//1行ずつ、nullになるまで繰り返す  
	while ((s = bufferedReader.readLine()) != null) {  
		//配列に追加  
		quizSet.add(s.split("\t"));  
	}  
}  
catch (IOException e) { //例外処理  
	e.printStackTrace();  
}
finally {   //必ず実行  
	try {  
		if (inputStream != null) inputStream.close();  
		if (bufferedReader != null) bufferedReader.close();  
	}  
	catch (IOException e) {  
		e.printStackTrace();  
	}  
}

## 配列
	* 通常の配列
		型名[] 変数名
		* アクセス方法 ⇛ 変数名[要素番号]
	* ArrayListクラス
		ArrayList<型名> 変数名 = new ArrayList<型名>();
		* アクセス方法 ⇛ 変数名.get(要素番号)
		* 追加方法 ⇛ 変数名.add(data)

## ListView
**width , heightは必ずmatch_parentにする！！！**

## DB使用時のコントラクトクラス
	* 定数定義の為、classはfainalを付け念の為空のコンストラクタを定義しておく
