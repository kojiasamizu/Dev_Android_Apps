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
		intent.putExtra(KEY , VALUE); //繧ｭ繝ｼ縺ｨ蛟､縺ｮ繝壹い縺ｧ貂｡縺�(KEY縺ｯ螳壽焚繧呈耳螂ｨ)  

	3. 遷移開始  
		startActivity(intent);
