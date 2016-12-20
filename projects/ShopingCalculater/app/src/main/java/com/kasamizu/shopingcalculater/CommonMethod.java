package com.kasamizu.shopingcalculater;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by kasamizu on 2016/12/06.
 */

public class CommonMethod {

	public String getTvStr(TextView tv) {
		return tv.getText().toString().trim();
	}

	public String getEditStr(EditText et) {
		return et.getText().toString().trim();
	}

	public String getBtnStr(Button b) {
		return b.getText().toString().trim();
	}

	public void setCursorLast(EditText et) {
		et.setSelection(et.getText().length());
	}

	public boolean isFloor(double d) {
		boolean r = false;

		if (d - Math.floor(d) != 0) {
			r = true;
		}

		return r;
	}
}
