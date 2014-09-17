package com.mavericks.checkin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class HCPaymentActivity extends Activity implements OnClickListener{
	TextView mTxtpayment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcpayment);
		mTxtpayment = (TextView) findViewById(R.id.text_payment);
		mTxtpayment.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.text_payment:
		

			break;
		default:
			break;
		}
	}

}
