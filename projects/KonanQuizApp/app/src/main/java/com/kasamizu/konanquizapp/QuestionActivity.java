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

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{
	public final static int REQUEST_CODE				= 1;
	public final static String EXTRA_ANSWER_RESULT		= "com.kasamizu.konanquizapp.EXTRA_ANSWER_RESULT";
	public final static String EXTRA_CORRECT_COUNT		= "com.kasamizu.konanquizapp.EXTRA_CORRECT_COUNT";
	public final static String EXTRA_MAX_QUIZ_COUNT		= "com.kasamizu.konanquizapp.EXTRA_MAX_QUIZ_COUNT";
	public final static String EXTRA_LAST_QUIZ_FLAG		= "com.kasamizu.konanquizapp.EXTRA_LAST_QUIZ_FLAG";

	private int currentQuizId							= 0;
	private int correctAnswerCount						= 0;
	private TextView questionText						= null;
	private ArrayList<String[]> quizMasterData			= new ArrayList<String[]>();
	private ArrayList<String[]> quizData				= new ArrayList<String[]>();
	private ArrayList<Button> selectBtnList 			= new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question);

		loadquizMasterData();
		setQuizData();
		getViews();
		setQuizText();
		setClickEvent();
	}

	private void setClickEvent() {
		for (int i = 0 ; i < this.selectBtnList.size() ; i++) {
			Button b = (Button) this.selectBtnList.get(i);
			b.setOnClickListener(this);
		}
	}


	private void loadquizMasterData() {
		InputStream inputStream = null;
		BufferedReader bufferedReader = null;

		try {
			//テキストファイル取得
			inputStream = getAssets().open("quiz.txt");
			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

			//1行分の一時保存変数
			String tmpStr;
			while ((tmpStr = bufferedReader.readLine()) != null) {
				//カンマ区切りで格納
				quizMasterData.add(tmpStr.split(","));
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (inputStream != null) inputStream.close();
				if (bufferedReader != null) bufferedReader.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getViews() {
		questionText = (TextView) findViewById(R.id.questionText);

		selectBtnList = new ArrayList<Button>();
		selectBtnList.add((Button) findViewById(R.id.selectBtn0));
		selectBtnList.add((Button) findViewById(R.id.selectBtn1));
		selectBtnList.add((Button) findViewById(R.id.selectBtn2));
		selectBtnList.add((Button) findViewById(R.id.selectBtn3));

	}

	private void setQuizData() {
		quizData = new ArrayList<String[]>();

		Intent intent = getIntent();
		int chapterId = intent.getIntExtra(MainActivity.EXTRA_QUIZ_CHAPTER , 1);
		for (int i = 0 ; i < quizMasterData.size() ; i++) {
			if (chapterId == Integer.parseInt(quizMasterData.get(i)[5])) {
				quizData.add(quizMasterData.get(i));
			}
		}
	}

	private void setQuizText() {
		String[] qData = getCurrentData();

		questionText.setText(qData[0]);

		ArrayList<String> answers = new ArrayList<String>();
		for (int i = 1 ; i <= this.selectBtnList.size() ; i++) {
			Log.d("ans" , qData[i]);
			answers.add(qData[i]);
		}
		Collections.shuffle(answers);

		for (int i = 0 ; i < selectBtnList.size() ; i++) {
			selectBtnList.get(i).setText(answers.get(i));
		}
	}

	private String[] getCurrentData() {
		return this.quizData.get(this.currentQuizId);
	}

	public void selectAnswer(View v) {
		String ansResult = (isCorrectAnswer((Button) v)) ? "CORRECT!" : "INCORRECT...";
		if (ansResult == "CORRECT") this.correctAnswerCount++;

		//画面遷移
		Intent intent = new Intent(this , ResultActivity.class);
		intent.putExtra(EXTRA_ANSWER_RESULT , ansResult);
		intent.putExtra(EXTRA_LAST_QUIZ_FLAG , isLastQuiz());
		if (isLastQuiz()) {
			startActivity(intent);
		}
		else {
			startActivityForResult(intent, REQUEST_CODE);
			return;
		}
	}

	private boolean isCorrectAnswer(Button b) {
		return (b.getText().toString() == getCurrentData()[1]) ? true : false;
	}

	private boolean isLastQuiz() {
		return (this.correctAnswerCount > (quizData.size() - 1)) ? true : false;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);

		if (requestCode == REQUEST_CODE) {
			this.currentQuizId++;
		}

		setQuizText();
	}

	public void onClick(View v) {
		if (v == null) return;

		int id = v.getId();

		if (isClickSelectBtn(id)) {
			selectAnswer(v);
		}

	}

	private boolean isClickSelectBtn(int id) {
		boolean r = false;

		for (int i = 0 ; i < this.selectBtnList.size() ; i++) {
			if (id == this.selectBtnList.get(i).getId()) {
				r = true;
				break;
			}
		}

		return r;
	}


	private void debug(String d) {
		Log.d("DEBUG_LOG" , d);
	}
}
