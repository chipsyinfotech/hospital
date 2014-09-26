
package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gcm.GCMRegistrar;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.client.HCSession;
import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.parser.HCStatusParser;
import com.mavericks.checkin.utils.HCAlertManager;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

public class HCSignupActivity extends HCBaseActivity implements OnClickListener {
	EditText mEdtemail;
	EditText mEdtdigit;
	EditText mEdtconfirmdigit;
	TextView mTxtsignup;
	String email;
	String digit;
	String confirmdigit;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcsignup);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mEdtdigit = (EditText) findViewById(R.id.edt_digit);
		mEdtconfirmdigit = (EditText) findViewById(R.id.edt_confirmdigit);
		mTxtsignup = (TextView) findViewById(R.id.text_signup);
		mTxtsignup.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.text_signup:
			if (isFormValid())
				signup();
			
			break;
		default:
			break;
		}

	}
	
	
	private boolean isFormValid() {
		email= mEdtemail.getText().toString();
		digit= mEdtdigit.getText().toString();
		confirmdigit= mEdtconfirmdigit.getText().toString();
		int msg = 0;
		boolean bValid = true;
		if (mEdtemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			bValid = false;
		} else if (mEdtdigit.getText().toString().trim().length() == 0) {
			msg = R.string.err_digit;
			bValid = false;
		} else if (mEdtconfirmdigit.getText().toString().trim().length() == 0) {
			msg = R.string.err_confrmdigit;
			bValid = false;
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			mEdtemail.setText("");
			bValid = false;
			
		} else if (!mEdtdigit.getText().toString()
				.equals(mEdtconfirmdigit.getText().toString())) {
			msg = R.string.err_pswrd_mismatch;
			mEdtconfirmdigit.setText("");
			mEdtdigit.setText("");
			bValid = false;	
		} if (msg != 0)
		HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;		


	}
	private void signup() {
		
			showProgressDialog(null, false);
			final HCStatusParser parser = new HCStatusParser();

			
			List<NameValuePair> formData = new ArrayList<NameValuePair>();
			formData.add(new BasicNameValuePair(HCConstants.PAR_EMAIL_ID, ""+mEdtemail.getText().toString()));		
			formData.add(new BasicNameValuePair(HCConstants.PAR_LOGIN_TYPE, "hospital"));
			formData.add(new BasicNameValuePair(HCConstants.PAR_PSWRD, ""+mEdtdigit.getText().toString()));
			if(isRegisterd())
				formData.add(new BasicNameValuePair(HCConstants.PAR_APP_KEY, ""+GCMRegistrar.getRegistrationId(this)));
			
			
			HCClient.getInstance().request(this, HCServerUtils.REQ_HOSPITAL_REG,
					null, formData, null, parser, new HCIRequestListener() {
									
				
				@Override
						public void onComplete(int req_type, int status) {
							hideProgressDialog();
							if(status == HCConstants.ERROR_CODE_SUCCESS) {
							
								startActivity(new Intent(HCSignupActivity.this,HCSignInActivity.class));
								overridePendingTransition(R.anim.slide_in_top_scr, 0);
								HCSession.getInstance().storeSession(HCSignupActivity.this,
										(HCProfileHolder) parser.getDataHolder());
								setResult(RESULT_OK);
								finish();								
							}
							else {
								HCAlertManager.showAlertWithOneBtn(HCSignupActivity.this, 
										parser.getStatusMessage(), null);
							}
							
						}
					});
		}
		
		private boolean isRegisterd() {
			String regId =  GCMRegistrar.getRegistrationId(this);
			 if((HCUtils.getPrefBool(HCConstants.PREF_DIV_REG, false, this) ||
					(regId != null && !TextUtils.isEmpty(regId))))
					return true;
			 else
				 return false;
		}
		@Override
		public void onBackPressed() {
			
			super.onBackPressed();
			overridePendingTransition(0,android.R.anim.fade_out);
		}
	}

		
		