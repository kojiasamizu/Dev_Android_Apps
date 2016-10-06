# ドットインストール簡易メモ  

## 修飾子  
	* public  
		クラス外からのアクセス許可
	* private  
		クラス外からのアクセス禁止
	* static  
		インスタンスを生成せずに、アクセス可能

## エラーの表示
	1. EditText.setError(str);  
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
