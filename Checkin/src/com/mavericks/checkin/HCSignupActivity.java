
package com.mavericks.checkin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HCSignupActivity extends Activity implements OnClickListener {
	EditText mEdtemail;
	EditText mEdtdigit;
	EditText mEdtconfirmdigit;
	Button mBtnsignup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcsignup);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mEdtdigit = (EditText) findViewById(R.id.edt_digit);
		mEdtconfirmdigit = (EditText) findViewById(R.id.edt_confirmdigit);
		mBtnsignup = (Button) findViewById(R.id.btn_signup);
		mBtnsignup.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_signup:
			isFormValid();

			break;
		default:
			break;
		}

	}

	
	private void isFormValid() {

		if (mEdtemail.getText().toString().trim().length() == 0) {
			Toast.makeText(this, "please enter your email.", Toast.LENGTH_LONG)
					.show();
		} else if (mEdtdigit.getText().toString().trim().length() == 0) {
			Toast.makeText(this, "please enter your pin.", Toast.LENGTH_LONG)
					.show();
		} else if (mEdtconfirmdigit.getText().toString().trim().length() == 0) {
			Toast.makeText(this, "please  re enter your pin.",
					Toast.LENGTH_LONG).show();
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			Toast.makeText(this, "please enter a valid email",
					Toast.LENGTH_LONG).show();

		} else if (!mEdtdigit.getText().toString()
				.equals(mEdtconfirmdigit.getText().toString())) {
			Toast.makeText(this, "password mismatch", Toast.LENGTH_LONG).show();
		} else
			Toast.makeText(this, "Signup successful", Toast.LENGTH_LONG).show();

	}

}
