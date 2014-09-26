/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCRegistrationActivity.java
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
 */

package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCHospitalSession;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCHospitalHolder;
import com.mavericks.checkin.parser.HCVisitParser;
import com.mavericks.checkin.utils.HCAlertManager;
import com.mavericks.checkin.utils.HCConstants;

public class HCRegistrationActivity extends HCBaseActivity implements
		OnClickListener {

	LinearLayout lDoctor;
	LinearLayout lVisit;
	LinearLayout rVisit;
	RadioGroup rgoption;
	RadioGroup rgvisit;
	RadioGroup rggender;
	RadioButton yes;
	RadioButton no;
	RadioButton male;
	RadioButton female;
	RadioButton visit;
	RadioButton revisit;
	Spinner mSpnhospital;
	Spinner mSpnspecialization;
	Spinner mSpndate;
	Spinner mSpndoctor;
	Spinner mSpntime;
	TextView login;
	TextView mTxtvcheckin;
	TextView mTxtrcheckin;
	EditText mEdtvname;
	EditText mEdtvfname;
	EditText mEdtvage;
	EditText mEdtvhnumber;
	EditText mEdtvmobile;
	EditText mEdtvemail;
	EditText mEdtvaddress;
	EditText mEdtvlocation;
	EditText mEdtrname;
	EditText mEdtrhnumber;
	EditText mEdtrmobile;
	EditText mEdtremail;
	TextView mTxtappointment;
	String usernumber;
	String mUserid = null;
	String lname = null;
	String prioritypass = null;
	String appointment=null;
	TextView mTxtgender;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcregistration);
		mSpnhospital = (Spinner) findViewById(R.id.spinner_hospital);
		mSpnspecialization = (Spinner) findViewById(R.id.spinner_special);
		mSpndate = (Spinner) findViewById(R.id.spinner_date);
		mSpndoctor = (Spinner) findViewById(R.id.spinner_doctor);
		mSpntime = (Spinner) findViewById(R.id.spinner_time);
		login = (TextView) findViewById(R.id.text_log);
		mEdtvname = (EditText) findViewById(R.id.edt_pname);
		mEdtvfname = (EditText) findViewById(R.id.edt_fname);
		mEdtvage = (EditText) findViewById(R.id.edt_age);
		mEdtvhnumber = (EditText) findViewById(R.id.edt_hnumber);
		mEdtvmobile = (EditText) findViewById(R.id.edt_mobile);
		mEdtvemail = (EditText) findViewById(R.id.edt_email);
		mEdtvaddress = (EditText) findViewById(R.id.edt_address);
		mEdtvlocation = (EditText) findViewById(R.id.edt_location);
		mEdtrname = (EditText) findViewById(R.id.edt_rname);

		mEdtrhnumber = (EditText) findViewById(R.id.edt_rhnumber);
		mEdtrmobile = (EditText) findViewById(R.id.edt_rmobile);
		mEdtremail = (EditText) findViewById(R.id.edt_remail);
		mTxtappointment = (TextView) findViewById(R.id.text_appointment);
		mTxtgender = (TextView) findViewById(R.id.text_gender);
		mTxtvcheckin = (TextView) findViewById(R.id.text_visit);
		mTxtrcheckin = (TextView) findViewById(R.id.text_revisit);

		lDoctor = (LinearLayout) findViewById(R.id.lin_doctor);
		lVisit = (LinearLayout) findViewById(R.id.lin_visit);
		rVisit = (LinearLayout) findViewById(R.id.lin_revisit);
		rgoption = (RadioGroup) findViewById(R.id.radio);
		rgvisit = (RadioGroup) findViewById(R.id.visit);
		rggender = (RadioGroup) findViewById(R.id.radio_gender);
		yes = (RadioButton) findViewById(R.id.radio_yes);
		no = (RadioButton) findViewById(R.id.radio_no);
		male = (RadioButton) findViewById(R.id.radio_male);
		female = (RadioButton) findViewById(R.id.radio_female);
		visit = (RadioButton) findViewById(R.id.radio_visit);
		revisit = (RadioButton) findViewById(R.id.radio_revisit);

		yes.setOnClickListener(this);
		no.setOnClickListener(this);
		login.setOnClickListener(this);
		visit.setOnClickListener(this);
		revisit.setOnClickListener(this);
		mTxtvcheckin.setOnClickListener(this);
		mTxtrcheckin.setOnClickListener(this);
		lDoctor.setVisibility(View.GONE);
		lVisit.setVisibility(View.VISIBLE);
		rVisit.setVisibility(View.GONE);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_visit:
			if (isFormNewValid())
				newvisit();

		case R.id.text_revisit:
			if (isFormRevisitValid())
				revisit();

		case R.id.radio_yes:
			lDoctor.setVisibility(View.VISIBLE);
			break;
		case R.id.text_log:
			login();

			break;
		case R.id.radio_no:
			lDoctor.setVisibility(View.INVISIBLE);
			findViewById(R.id.lin_doctor).setVisibility(View.GONE);
			break;
		case R.id.radio_visit:
			lVisit.setVisibility(View.VISIBLE);

			if (findViewById(R.id.lin_revisit).getVisibility() == View.VISIBLE)
				findViewById(R.id.lin_revisit).setVisibility(View.INVISIBLE);

			break;
		case R.id.radio_revisit:
			rVisit.setVisibility(View.VISIBLE);

			if (findViewById(R.id.lin_visit).getVisibility() == View.VISIBLE)
				findViewById(R.id.lin_visit).setVisibility(View.INVISIBLE);
			break;
		case R.id.spinner_hospital:spinner();
		break;
		
		default:
			break;
		}

	}

	private boolean isFormNewValid() {

		int msg = 0;
		boolean bValid = true;
	

		if (mEdtvname.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			bValid = false;
		} else if (mEdtvfname.getText().toString().trim().length() == 0) {
			msg = R.string.err_fname;
			bValid = false;
		} else if (mEdtvage.getText().toString().trim().length() == 0) {
			msg = R.string.err_age;
			bValid = false;
		} else if (mEdtvhnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			bValid = false;
		} else if (mEdtvmobile.getText().toString().trim().length() == 0) {
			msg = R.string.err_mobile;
			bValid = false;
		} else if (mEdtvemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			bValid = false;
		} else if (mEdtvaddress.getText().toString().trim().length() == 0) {
			msg = R.string.err_address;
			bValid = false;
		} else if (mEdtvlocation.getText().toString().trim().length() == 0) {
			msg = R.string.err_location;
			bValid = false;
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtvemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			mEdtvemail.setText("");
			bValid = false;
		} else if (mEdtvmobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			mEdtvmobile.setText("");
			bValid = false;
		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;

	}

	private boolean isFormRevisitValid() {

		int msg = 0;
		boolean bValid = true;
		

		 if (mEdtrname.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			bValid = false;
		}

		else if (mEdtrhnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			bValid = false;
		} else if (mEdtrmobile.getText().toString().trim().length() == 0) {
			msg = R.string.err_mobile;
			bValid = false;
		} else if (mEdtremail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			bValid = false;
		}

		else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtremail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			mEdtremail.setText("");
			bValid = false;
		} else if (mEdtrmobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			mEdtrmobile.setText("");
			bValid = false;
		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;

	}

	private void newvisit() {
		showProgressDialog(null, false);
		final HCVisitParser parser = new HCVisitParser();

		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID, mUserid));
		formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID, ""
				+ mSpnspecialization.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE, ""
				+ mSpndate.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_TIME_ID, ""
				+ mSpntime.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_FNAME, ""
				+ mEdtvname.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_LNAME, lname));
		formData.add(new BasicNameValuePair(HCConstants.PAR_MOB_NUMB, ""
				+ mEdtvmobile.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID, ""
				+ mSpnhospital.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_PASS, prioritypass));
		formData.add(new BasicNameValuePair(HCConstants.PAR_FANAME, ""
				+ mEdtvfname.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_GENDER, ""
				+ mTxtgender.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_ADDRESS, ""
				+ mEdtvaddress.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_AGE, ""
				+ mEdtvage.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_APPOINTMENT, appointment));
		formData.add(new BasicNameValuePair(HCConstants.PAR_DNAME, ""
				+ mSpndoctor.getContext().toString()));
		HCClient.getInstance().request(this, HCServerUtils.REQ_HOSPITAL_VISIT,
				null, formData, null, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						startActivity(new Intent(HCRegistrationActivity.this,
								HCPaymentActivity.class));
						HCHospitalSession.getInstance().storeSession(
								HCRegistrationActivity.this,
								(HCHospitalHolder) parser.getDataHolder());
						finish();

					}
				});

	}

	private void revisit() {
		showProgressDialog(null, false);
		final HCVisitParser parser = new HCVisitParser();

		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID, mUserid));
		formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID, ""
				+ mSpnspecialization.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE, ""
				+ mSpndate.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_TIME_ID, ""
				+ mSpntime.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_FNAME, ""
				+ mEdtrname.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_LNAME, lname));
		formData.add(new BasicNameValuePair(HCConstants.PAR_MOB_NUMB, ""
				+ mEdtrmobile.getText().toString()));

		formData.add(new BasicNameValuePair(HCConstants.PAR_HNUMBER, usernumber));
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID, ""
				+ mSpnhospital.getContext().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_PASS, prioritypass));

		formData.add(new BasicNameValuePair(HCConstants.PAR_APPOINTMENT, appointment));
		formData.add(new BasicNameValuePair(HCConstants.PAR_DNAME, ""
				+ mSpndoctor.getContext().toString()));
		HCClient.getInstance().request(this,
				HCServerUtils.REQ_HOSPITAL_REVISIT, null, formData, null,
				parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						startActivity(new Intent(HCRegistrationActivity.this,
								HCPaymentActivity.class));
						
						HCHospitalSession.getInstance().storeSession(
								HCRegistrationActivity.this,
								(HCHospitalHolder) parser.getDataHolder());
						
						finish();
					}
				});

	}

private void spinner()
{
	
	
}
private void login()
{
	startActivity(new Intent(HCRegistrationActivity.this,
			HCSignInActivity.class));
	overridePendingTransition(R.anim.anim_from_middle, 0);
}
@Override
public void onBackPressed() {
	
	super.onBackPressed();
	overridePendingTransition(0,android.R.anim.fade_out);
}
}
