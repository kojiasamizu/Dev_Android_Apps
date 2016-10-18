package com.kasamizu.myanagramapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

	private EditText myEditText;
	public final static String EXTRA_INPUT_TEXT = "com.kasamizu.myanagramapp.EXTRA_INPUT_TEXT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myEditText = (EditText) findViewById(R.id.myEditText);
		//myEditText.setText("");
	}

	@Override
	protected void onStart() {
		super.onStart();
		myEditText.setText("");
	}

	//入力窓onClick
	public void clearText(View view) {
		myEditText.setText("");
	}

	//CREATEボタンonClick
	public void createAnagram(View view) {
		//入力文字列
		String inputText = myEditText.getText().toString().trim();
		//文字列シャッフル用リスト
		ArrayList<String> list = new ArrayList<String>();

		//リストの作成
		for (int i = 0 ; i < inputText.length() ; i++) {
			list.add(inputText.substring(i , (i + 1)));
		}

		//シャッフル
		Collections.shuffle(list);

		//結果のテキスト
		String resultText = "";
		for (int i = 0 ; i < list.size() ; i++) {
			resultText += list.get(i);
		}

		//表示
		//myEditText.setText(resultText);

		//画面遷移
		Intent intent = new Intent(this , MyResultApp.class);
		intent.putExtra(EXTRA_INPUT_TEXT , resultText);
		startActivity(intent);
	}
}
