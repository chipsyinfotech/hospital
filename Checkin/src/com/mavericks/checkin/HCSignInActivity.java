/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		 HCSignInActivity.java
 * @Project:
 *		Checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 09-Sep-2014
 */package com.mavericks.checkin;

 import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HCSignInActivity extends Activity implements OnClickListener {
	EditText mEdtemail;
	EditText mEdtdigit;
	Button mBtnlogin;
	TextView mTxtpaswrd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcsign_in);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mEdtdigit = (EditText) findViewById(R.id.edt_digit);
		mTxtpaswrd=(TextView) findViewById(R.id.text_password);
		mBtnlogin=(Button) findViewById(R.id.btn_login);
		mBtnlogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.text_password:
			
		case R.id.btn_login:
			login();

			break;
		default:
			break;
		}

	}
		
	
	private void login() {

		if (mEdtemail.getText().toString().trim().length() == 0) {
			Toast.makeText(this, "please enter your email.", Toast.LENGTH_LONG)
					.show();
		} else if (mEdtdigit.getText().toString().trim().length() == 0) {
			Toast.makeText(this, "please enter your pin.", Toast.LENGTH_LONG)
					.show();
		
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			Toast.makeText(this, "please enter a valid email",
					Toast.LENGTH_LONG).show();

		
		} else
			Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();

	}

}



