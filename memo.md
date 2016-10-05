# ドットインストール簡易メモ  
***  
## 修飾子  
	* public  
		クラス外からのアクセス許可
	* private  
		クラス外からのアクセス禁止
	* **static**  
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
