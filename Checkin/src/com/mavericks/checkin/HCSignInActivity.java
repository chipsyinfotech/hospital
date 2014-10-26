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

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.client.HCSession;
import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.parser.HCForgotParser;
import com.mavericks.checkin.parser.HCStatusParser;
import com.mavericks.checkin.utils.HCAlertManager;
import com.mavericks.checkin.utils.HCConstants;

public class HCSignInActivity extends HCBaseActivity implements OnClickListener {
	EditText mEdtemail;
	EditText mEdtdigit;
	TextView mTxtlogin;
	TextView mTxtpaswrd;
	String email;
	String digit;
	TextView mTxtsignup;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcsign_in);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mEdtdigit = (EditText) findViewById(R.id.edt_digit);
		mTxtpaswrd = (TextView) findViewById(R.id.text_password);
		mTxtsignup = (TextView) findViewById(R.id.text_signup);
		mTxtlogin = (TextView) findViewById(R.id.text_login);
		mTxtlogin.setOnClickListener(this);
		mTxtpaswrd.setOnClickListener(this);
		mTxtsignup.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_password:
			if (isForgotValid())
				ForgotPassword();
			break;

		case R.id.text_signup:
			signup();
		case R.id.text_login:

			if (isFormValid())
				login();

			break;
		default:
			break;
		}

	}

	private boolean isForgotValid() {
		int msg = 0;
		boolean bValid = true;

		if (mEdtemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			mEdtemail.setText("");
			bValid = false;
		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;
	}

	private boolean isFormValid() {
		email = mEdtemail.getText().toString();
		digit = mEdtdigit.getText().toString();

		int msg = 0;
		boolean bValid = true;
		if (mEdtemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (mEdtdigit.getText().toString().trim().length() == 0) {
			msg = R.string.err_digit;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;

		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			mEdtemail.setText("");
			bValid = false;

		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;

	}

	/**
	 * function to request forgot password
	 */
	private void ForgotPassword() {
		showProgressDialog(null, false);
		final HCForgotParser parser = new HCForgotParser();
		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PAR_EMAIL_ID, ""
				+ mEdtemail.getText().toString()));

		HCClient.getInstance().request(this, HCServerUtils.REQ_FORGOT_PASS,
				null, formData, null, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();

						if (status == HCConstants.ERROR_CODE_SUCCESS) {

							HCAlertManager.showAlertWithOneBtn(
									HCSignInActivity.this,
									getString(R.string.forgot), null);
						} else {
							HCAlertManager.showAlertWithOneBtn(
									HCSignInActivity.this,
									parser.getStatusMessage(), null);
						}

					}
				});

	}

	private void login() {

		showProgressDialog(null, false);
		final HCStatusParser parser = new HCStatusParser();

		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PAR_EMAIL_ID, ""
				+ mEdtemail.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_PSWRD, ""
				+ mEdtdigit.getText().toString()));
		HCClient.getInstance().request(this, HCServerUtils.REQ_HOSPITAL_LOGIN,
				null, formData, null, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {

							startActivity(new Intent(HCSignInActivity.this,
									HCRegistrationActivity.class));
							overridePendingTransition(R.anim.anim_from_middle,
									0);
							HCSession.getInstance().storeSession(
									HCSignInActivity.this,
									(HCProfileHolder) parser.getDataHolder());
							finish();
						} else {
							HCAlertManager.showAlertWithOneBtn(
									HCSignInActivity.this,
									parser.getStatusMessage(), null);
						}

					}
				});

	}

	private void signup() {
		startActivity(new Intent(HCSignInActivity.this, HCSignupActivity.class));
		overridePendingTransition(R.anim.slide_in_right, 0);
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		overridePendingTransition(0, android.R.anim.fade_out);
	}
}
