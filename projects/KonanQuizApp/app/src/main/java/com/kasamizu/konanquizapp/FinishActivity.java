package com.kasamizu.konanquizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		setAnswerResult();
	}

	private void setAnswerResult() {
		TextView t = (TextView) findViewById(R.id.answerResultText);
		Intent intent = getIntent();
		String correctCount	= String.valueOf(intent.getIntExtra(QuestionActivity.EXTRA_CORRECT_COUNT , 0));
		String quizCount	= String.valueOf(intent.getIntExtra(QuestionActivity.EXTRA_MAX_QUIZ_COUNT , 0));

		t.setText(correctCount + "/" + quizCount);
	}
}
