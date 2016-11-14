package com.kasamizu.konanquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

	public final static String EXTRA_NEXT_QUIZ_ID	= "com.kasamizu.konanquizapp.EXTRA_NEXT_QUIZ_ID";

	private Intent intent							= getIntent();
	private boolean isLastQuiz 						= intent.getBooleanExtra(QuestionActivity.EXTRA_LAST_QUIZ_FLAG , false);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		setResultText();

		//最後の問題だった場合
		if (isLastQuiz) {
			Button b = (Button) findViewById(R.id.nextBtn);
			b.setText("GoResult");
		}
	}

	private void setClickEvent() {
		findViewById(R.id.retryBtn).setOnClickListener(this);
		findViewById(R.id.nextBtn).setOnClickListener(this);
	}

	private void setResultText() {
		TextView rt = (TextView) findViewById(R.id.resultText);
		String text = intent.getStringExtra(QuestionActivity.EXTRA_ANSWER_RESULT);
		rt.setText(text);
	}

/*
	public void retryQuiz(View view) {
		finish();
	}

	public void nextQuiz(View view) {
		intent = new Intent();
		setResult(RESULT_OK , intent);
		finish();
	}
*/
	public void onClick(View v) {
		int id = v.getId();

		//retry
		if (id == R.id.retryBtn) {
			finish();
		}
		else {
			//最後の問題の時
			if (isLastQuiz) {
				/*
				//画面遷移
				Intent data = new Intent(this , FinishActivity.class);
				data.putExtra(EXTRA_MAX_QUIZ_COUNT , this.quizData.size());
				data.putExtra(EXTRA_CORRECT_COUNT , this.correctAnswerCount);
				startActivity(data);
				*/
			}
		}
	}
}
