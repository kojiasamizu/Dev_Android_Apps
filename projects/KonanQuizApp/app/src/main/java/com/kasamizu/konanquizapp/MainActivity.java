package com.kasamizu.konanquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	public final static String EXTRA_QUIZ_CHAPTER		= "com.kasamizu.konanquizapp.EXTRA_QUIZ_CHAPTER";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setClickEvent();
	}

	private void setClickEvent() {
		//view取得
		findViewById(R.id.chapterButton1).setOnClickListener(this);
		findViewById(R.id.chapterButton2).setOnClickListener(this);
		findViewById(R.id.chapterButton3).setOnClickListener(this);
		findViewById(R.id.chapterButton4).setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v == null) {
			return;
		}

		int id		= v.getId();
		int chapter = 1;

		if (id == R.id.chapterButton1) chapter = 1;
		if (id == R.id.chapterButton2) chapter = 2;
		if (id == R.id.chapterButton3) chapter = 3;
		if (id == R.id.chapterButton4) chapter = 4;

		startQuiz(chapter);
	}
	public void startQuiz(int chapter) {
		Intent intent = new Intent(this , QuestionActivity.class);
		intent.putExtra(EXTRA_QUIZ_CHAPTER , chapter);
		startActivity(intent);
	}
}
