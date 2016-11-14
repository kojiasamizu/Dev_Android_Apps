package com.kasamizu.myanagramapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MyResultApp extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_result_app);

		TextView resultText = (TextView) findViewById(R.id.resultText);
		Intent intent = getIntent();
		resultText.setText(intent.getStringExtra(MainActivity.EXTRA_INPUT_TEXT));
	}

	public void goOtherWord(View view) {
		finish();
	}
}