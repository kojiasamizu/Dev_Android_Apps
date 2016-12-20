package com.kasamizu.shopingcalculater;

import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

	/*********
	 * Field *
	 *********/

	//Views
	private EditText resultText;
	private TextView culcType;
	private double result		= 0.0;
	private boolean isInputing	= false;



	//Variables
		/**********
	 * Method *
	 **********/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		this.resultText	= (EditText) findViewById(R.id.resultText);
		this.culcType	= (TextView) findViewById(R.id.culcType);
		setClickEvent();
		dispResult();
	}

	private void clickNumber(Button b) {
		if (!this.isInputing) {
			this.isInputing = true;
			this.resultText.setText("");
		}
		setResultText(getResultText() + getClickText(b));
	}

	private void clickDot(Button b) {
		String rText 	= getResultText();
		String dot		= getString(R.string.dot);
		if (rText.indexOf(dot) >= 0 || rText == "") return;
		if (!this.isInputing) this.isInputing = true;
		setResultText(rText + dot);
	}

	private String getResultText() {
		return this.resultText.getText().toString().trim();
	}
	private String getClickText(Button b) {
		return b.getText().toString().trim();
	}
	private String getCulcType() {
		return this.culcType.getText().toString().trim();
	}

	private void setResultText (String s) {
		this.resultText.setText(s);
		this.resultText.setSelection(this.resultText.getText().length());
	}

	private void clickCulcType(Button b) {
		if (this.isInputing) {
			this.isInputing = false;
			culcResult();
			dispResult();
		}

		String s = getClickText(b);
		int id = b.getId();
		if (id == R.id.discountPerBtn) 			s = getString(R.string.percentStr);
		else if (id == R.id.discountRateBtn) 	s = getString(R.string.rateStr);
		else if (id == R.id.equalBtn) 			s = "";
		this.culcType.setText(s);
	}

	private void culcResult() {
		String nowType		= getCulcType();
		Double nowResult	= Double.parseDouble(getResultText());

		if (nowType == getString(R.string.addition)) 			this.result += nowResult;
		else if (nowType == getString(R.string.subtraction)) 	this.result -= nowResult;
		else if (nowType == getString(R.string.multiplication)) this.result *= nowResult;
		else if (nowType == getString(R.string.division)) 		this.result /= nowResult;
		else if (nowType == getString(R.string.percentStr)) 	this.result *= (100 - nowResult) / 100;
		else if (nowType == getString(R.string.rateStr)) 		this.result *= (10 - nowResult) / 10;
		else if (nowType == "")				 					this.result = nowResult;
	}

	private void clearOnce() {
		if (this.isInputing && getCulcType() != "") {
			dispResult();
			if (this.isInputing) this.isInputing = false;
		}
		else {
			clearAll();
		}
	}

	private void clearAll() {
		if (this.isInputing) this.isInputing = false;

		this.result = 0.0;
		this.culcType.setText("");
		dispResult();
	}

	private void culcTax() {
		if (this.isInputing) this.isInputing = false;
		Double nowResult = Double.parseDouble(getResultText());
		this.result = nowResult * 1.08;
		dispResult();
	}

	private void dispResult() {
		String s = String.valueOf(this.result);
		if (!isFloor(this.result)) {
			int idx = s.indexOf(getString(R.string.dot));
			s = s.substring(0 , idx);
		}
		setResultText(s);
	}
	private boolean isFloor(double d) {
		boolean r = false;

		if (d - Math.floor(d) != 0) {
			r = true;
		}

		return r;
	}

	private void setClickEvent() {
		View.OnClickListener listener;

		int[] numberId = {R.id.numBtn0 , R.id.numBtn1 , R.id.numBtn2 , R.id.numBtn3 , R.id.numBtn4 ,
				R.id.numBtn5 , R.id.numBtn6 , R.id.numBtn7 , R.id.numBtn8 , R.id.numBtn9};
		for (int i = 0 ; i < numberId.length ; i++) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					clickNumber((Button) v);
				}
			};
			findViewById(numberId[i]).setOnClickListener(listener);
		}
		//計算式のイベント
		int[] culcTypeId = {R.id.additionBtn , R.id.subtractionBtn , R.id.multiplicationBtn ,
				R.id.divisionBtn , R.id.equalBtn , R.id.discountPerBtn , R.id.discountRateBtn};
		for (int i = 0 ; i < culcTypeId.length ; i++) {
			listener = new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					clickCulcType((Button) v);
				}
			};
			findViewById(culcTypeId[i]).setOnClickListener(listener);
		}

		listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clickDot((Button) v);
			}
		};
		findViewById(R.id.dotBtn).setOnClickListener(listener);


		listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearOnce();
			}
		};
		findViewById(R.id.clearBtn).setOnClickListener(listener);

		listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clearAll();
			}
		};
		findViewById(R.id.clearAllBtn).setOnClickListener(listener);

		listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				culcTax();
			}
		};
		findViewById(R.id.taxBtn).setOnClickListener(listener);
	}
}
