/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		 HCSignInActivity.java
 * @Project:
 *		Checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright Â© 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/*! Revision history (Most recent first)
 Created by vijayalaxmi on 09-Sep-2014
 */package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCSpinnerAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCDateHolder;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.holders.HCSpecializationHolder;
import com.mavericks.checkin.holders.HCTimeHolder;
import com.mavericks.checkin.parser.HCDateParser;
import com.mavericks.checkin.parser.HCGetSpecializationParser;
import com.mavericks.checkin.parser.HCGetTimeParser;

import com.mavericks.checkin.utils.HCAlertManager;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCRetryDialog;
import com.mavericks.checkin.utils.HCRetryDialog.OnRetryClickListener;

public class HCRegistrationActivity extends HCBaseActivity implements
		OnClickListener {
	ImageView mImgmenu;
	TextView mTxtlog;
	Button mBtnhospital;
	Button mBtnspecial;;
	Button mBtndate;;
	RadioGroup mRadgroup;
	RadioButton mRadyes;
	RadioButton mRadno;
	Button mBtntime;;
	ImageView mImgdoctor;
	LinearLayout mLinvisit;
	LinearLayout mLinrevisit;
	RadioGroup mRadVisrevis;
	RadioButton mRadvisit;
	RadioButton mRadrevisit;
	EditText mEdtdoctor;
	LinearLayout mLinDoctor;
	EditText mEdtpname;
	EditText mEdtfname;
	EditText mEdtage;
	EditText mEdthnumber;
	EditText mEdtmobile;
	EditText mEdtemail;
	EditText mEdtaddress;
	EditText mEdtlocation;
	TextView mTxtdate;
	TextView mTxtnewcheck;
	EditText mEdtrepname;
	EditText mEdtrehnumber;
	EditText mEdtremobile;
	String married = null;
	String appointment = null;
	String userid = null;
	String hospital_id = "1234";
	String special_id = "101";
	String time_id = "12";
	String lname = null;
	String mname = null;
	String amount = null;
	String gender = null;
	String pass = "1";
	EditText mEdtremail;
	TextView mTxtrecheck;
	String hospital = "1001-140001";
	HCSpinnerAdapter mAdapter;
	String special = "101";
	String visit = null;
	HCLocationHolder mHolder;
	ArrayList<HCSpecializationHolder> mHosspecialization;
	
	ArrayList<HCTimeHolder> mHostime;
	ArrayList<HCDateHolder> mHosdate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Bundle bundle = getIntent().getExtras();
//		if (bundle.containsKey(HCConstants.EXTRA_HOSPITAL_LIST)) {
//			mHolder = bundle.getParcelable(HCConstants.EXTRA_HISTORY_LIST);
//		}

		

		setContentView(R.layout.activity_hcregistration);
		mImgmenu = (ImageView) findViewById(R.id.img_menu);
		mTxtlog = (TextView) findViewById(R.id.text_log);
		mRadgroup = (RadioGroup) findViewById(R.id.radio_app);
		mRadyes = (RadioButton) findViewById(R.id.radio_yes);

		mRadno = (RadioButton) findViewById(R.id.radio_no);
		mImgdoctor = (ImageView) findViewById(R.id.tab_doctor);
		mLinDoctor = (LinearLayout) findViewById(R.id.lin_doctor);
		mBtnhospital = (Button) findViewById(R.id.btn_sel_hos);
		//mBtnhospital.setText(mHolder.getmHosName());
		mBtnspecial = (Button) findViewById(R.id.btn_sel_special);
		mBtndate = (Button) findViewById(R.id.btn_sel_date);
		mBtntime = (Button) findViewById(R.id.btn_sel_time);
		mEdtpname = (EditText) findViewById(R.id.edt_pname);
		mEdtfname = (EditText) findViewById(R.id.edt_fname);
		mEdtage = (EditText) findViewById(R.id.edt_age);
		mEdthnumber = (EditText) findViewById(R.id.edt_hnumber);
		mEdtmobile = (EditText) findViewById(R.id.edt_mobile);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mEdtaddress = (EditText) findViewById(R.id.edt_address);
		mEdtlocation = (EditText) findViewById(R.id.edt_location);
		mTxtnewcheck = (TextView) findViewById(R.id.text_checkin);
		mEdtrepname = (EditText) findViewById(R.id.edt_repname);
		mEdtrehnumber = (EditText) findViewById(R.id.edt_rhnumber);
		mEdtremobile = (EditText) findViewById(R.id.edt_rmobile);
		mEdtremail = (EditText) findViewById(R.id.edt_remail);
		mTxtrecheck = (TextView) findViewById(R.id.text_recheckin);
		mLinvisit = (LinearLayout) findViewById(R.id.lin_visit);
		mRadVisrevis = (RadioGroup) findViewById(R.id.radio_visre);
		mRadvisit = (RadioButton) findViewById(R.id.radio_visit);
		mRadrevisit = (RadioButton) findViewById(R.id.radio_revisit);
		mLinrevisit = (LinearLayout) findViewById(R.id.lin_revisit);
		mEdtdoctor = (EditText) findViewById(R.id.edt_doctor);
		// set listener
		// set listener
		mRadyes.setOnClickListener(this);
		mRadno.setOnClickListener(this);
		mRadvisit.setOnClickListener(this);
		mBtndate.setOnClickListener(this);
		mBtntime.setOnClickListener(this);
		mBtnspecial.setOnClickListener(this);
		mRadrevisit.setOnClickListener(this);
		mTxtnewcheck.setOnClickListener(this);
		mTxtrecheck.setOnClickListener(this);
		mLinDoctor.setVisibility(View.GONE);
		mImgdoctor.setVisibility(View.GONE);
		mTxtlog.setOnClickListener(this);
		mLinrevisit.setVisibility(View.GONE);
		mAdapter = new HCSpinnerAdapter(this);
		getSpecialization();
		getTime();
		getDate();

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.radio_yes:
			mLinDoctor.findViewById(R.id.lin_doctor)
					.setVisibility(View.VISIBLE);
			mImgdoctor.setVisibility(View.VISIBLE);

			break;
		case R.id.radio_no:
			mLinDoctor.findViewById(R.id.lin_doctor).setVisibility(View.GONE);
			mImgdoctor.setVisibility(View.GONE);
			break;

		case R.id.btn_sel_special:
			showSpecialization();
			break;
		case R.id.btn_sel_date:
			showDate();

			break;
		case R.id.btn_sel_time:
			showTime();
			break;
		case R.id.btn_sel_hos:
			
			break;
		case R.id.radio_visit:
			mLinvisit.findViewById(R.id.lin_visit).setVisibility(View.VISIBLE);
			mLinrevisit.findViewById(R.id.lin_revisit).setVisibility(View.GONE);
			break;
		case R.id.radio_revisit:
			mLinrevisit.findViewById(R.id.lin_revisit).setVisibility(
					View.VISIBLE);
			mLinvisit.findViewById(R.id.lin_visit).setVisibility(View.GONE);
			break;
		case R.id.text_checkin:
			if (isFormNewValid())
				// newvisit();
				break;
		case R.id.text_log:

			startActivity(new Intent(HCRegistrationActivity.this,
					HCSignInActivity.class));
			overridePendingTransition(R.anim.slide_in_top_scr, 0);
		case R.id.text_recheckin:
			if (isFormRevisitValid())
				// revisit();
				break;
		default:
			break;
		}

	}


	private void getSpecialization() {
		showProgressDialog("", false);

		final HCGetSpecializationParser parser = new HCGetSpecializationParser();
		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				hospital));
		HCClient.getInstance().request(this,
				HCServerUtils.REQ_GET_SPECIALIZATION, null, null, formData,
				parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHosspecialization = (ArrayList<HCSpecializationHolder>) parser
									.getDataList();

						} else {
							// If we have data in database
							HCRetryDialog dialog = new HCRetryDialog(
									HCRegistrationActivity.this,
									HCServerUtils.REQ_GET_SPECIALIZATION);
							dialog.setOnRetryClickListener(new OnRetryClickListener() {

								@Override
								public void onRetryClick(int requestType) {
									getSpecialization();
								}
							});
							dialog.show(HCRegistrationActivity.this
									.getSupportFragmentManager(),
									HCHomeActivity.class.getSimpleName());
						}
					}
				});
	}
	private void showSpecialization() {

		mAdapter.setData(mHosspecialization);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select specialization")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						HCSpecializationHolder special = (HCSpecializationHolder) mAdapter
								.getItem(position);
						mBtnspecial.setText(special.getmHosSpecializationname());

						dialog.dismiss();
					}
				}).create().show();
	}

	
	private void getDate() {
		showProgressDialog("", false);

		final HCDateParser parser = new HCDateParser();
		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				hospital));
	
		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_DATE, null,
				null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHosdate = (ArrayList<HCDateHolder>) parser
									.getDataList();

						} else {
							// If we have data in database
							HCRetryDialog dialog = new HCRetryDialog(
									HCRegistrationActivity.this,
									HCServerUtils.REQ_GET_DATE);
							dialog.setOnRetryClickListener(new OnRetryClickListener() {

								@Override
								public void onRetryClick(int requestType) {
									getDate();
								}
							});
							dialog.show(HCRegistrationActivity.this
									.getSupportFragmentManager(),
									HCHomeActivity.class.getSimpleName());
						}
					}
				});
	}

	
	private void showDate() {

		mAdapter.setData(mHosdate);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select Date")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						HCDateHolder date = (HCDateHolder) mAdapter
								.getItem(position);
						mBtndate.setText(date.getDate());

						dialog.dismiss();
					}
				}).create().show();
	}
	
	private void getTime() {
		showProgressDialog("", false);

		final HCGetTimeParser parser = new HCGetTimeParser();
		List<NameValuePair> formData = new ArrayList<NameValuePair>();
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				hospital));
		formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID, special));
		formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE, visit));
		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_TIME, null,
				null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHostime = (ArrayList<HCTimeHolder>) parser
									.getDataList();

						} else {
							// If we have data in database
							HCRetryDialog dialog = new HCRetryDialog(
									HCRegistrationActivity.this,
									HCServerUtils.REQ_GET_TIME);
							dialog.setOnRetryClickListener(new OnRetryClickListener() {

								@Override
								public void onRetryClick(int requestType) {
									getTime();
								}
							});
							dialog.show(HCRegistrationActivity.this
									.getSupportFragmentManager(),
									HCHomeActivity.class.getSimpleName());
						}
					}
				});
	}
	
	
	private void showTime() {

		mAdapter.setData(mHostime);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select Time")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						HCTimeHolder time = (HCTimeHolder) mAdapter
								.getItem(position);
						mBtntime.setText(time.getAppointtime());

						dialog.dismiss();
					}
				}).create().show();
	}
	
	
	private boolean isFormNewValid() {
		int msg = 0;
		boolean bValid = true;
		if (mEdtpname.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			bValid = false;
		} else if (mEdtfname.getText().toString().trim().length() == 0) {
			msg = R.string.err_fname;
			bValid = false;
		} else if (mEdtage.getText().toString().trim().length() == 0) {
			msg = R.string.err_age;
			bValid = false;
		} else if (mEdthnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			bValid = false;
		} else if (mEdtmobile.getText().toString().trim().length() == 0) {
			msg = R.string.err_mobile;
			bValid = false;
		} else if (mEdtemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			bValid = false;
		} else if (mEdtaddress.getText().toString().trim().length() == 0) {
			msg = R.string.err_address;
			bValid = false;
		} else if (mEdtlocation.getText().toString().trim().length() == 0) {
			msg = R.string.err_location;
			bValid = false;
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				mEdtemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			mEdtemail.setText("");
			bValid = false;
		} else if (mEdtmobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			mEdtmobile.setText("");
			bValid = false;
		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;
	}

	private boolean isFormRevisitValid() {

		int msg = 0;
		boolean bValid = true;
		if (mEdtrepname.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			bValid = false;
		}

		else if (mEdtrehnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			bValid = false;
		} else if (mEdtremobile.getText().toString().trim().length() == 0) {
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
		} else if (mEdtremobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			mEdtremobile.setText("");
			bValid = false;
		} else if (mEdtremobile.getText().toString().trim().length() > 10) {
			msg = R.string.err_invalidphone;
			mEdtremobile.setText("");
			bValid = false;
		}

		if (msg != 0) {
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		}

		return bValid;

	}

	// /* newvisit */
	// private void newvisit() {
	// final HCVisitParser parser = new HCVisitParser();
	// List<NameValuePair> formData = new ArrayList<NameValuePair>();
	// List<NameValuePair> urlfields = new ArrayList<NameValuePair>(1);
	// urlfields.add(new BasicNameValuePair(HCConstants.PARAM_USERID, ""
	// + HCSession.getInstance().getUser_id(this)));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID,
	// hospital_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID,
	// special_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE, ""
	// + mBtndate.getContext().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_TIME_ID, time_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_FNAME, ""
	// + mEdtpname.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_LNAME, lname));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_MOB_NUMB, ""
	// + mEdtmobile.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_FANAME, ""
	// + mEdtfname.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_MOTHER, mname));
	//
	// formData.add(new BasicNameValuePair(HCConstants.PAR_MARRIED, married));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_AGE, ""
	// + mEdtage.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_GENDER, gender));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_ADDRESS, ""
	// + mEdtaddress.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_PASS, pass));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_TOTAL_AMOUNT,
	// amount));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_APPOINTMENT,
	// appointment));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_DNAME, ""
	// + mTxtdoctor.getText().toString()));
	// HCClient.getInstance().request(this, HCServerUtils.REQ_HOSPITAL_VISIT,
	// null, formData, null, parser, new HCIRequestListener() {
	//
	// @Override
	// public void onComplete(int req_type, int status) {
	// hideProgressDialog();
	// Intent intent = new Intent();
	// if (!HCUtils.isPrefExist(HCConstants.PREF_USRID,
	// HCRegistrationActivity.this))
	// intent.setClass(HCRegistrationActivity.this,
	// HCSignInActivity.class);
	// else
	// intent.setClass(HCRegistrationActivity.this,
	// HCPayActivity.class);
	// startActivity(intent);
	// finish();
	//
	// }
	// });
	// }
	//
	// /* revisit */
	// private void revisit() {
	// final HCVisitParser parser = new HCVisitParser();
	// List<NameValuePair> formData = new ArrayList<NameValuePair>();
	// formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID, ""
	// + HCSession.getInstance().getUser_id(this)));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID,
	// special_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE, ""
	// + mBtndate.getContext().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_TIME_ID, time_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_FNAME, ""
	// + mEdtrepname.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_LNAME, lname));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_MOB_NUMB, ""
	// + mEdtremobile.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_HNUMBER, ""
	// + mEdtrehnumber.getText().toString()));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID,
	// hospital_id));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_PASS, pass));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_TOTAL_AMOUNT,
	// amount));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_APPOINTMENT,
	// appointment));
	// formData.add(new BasicNameValuePair(HCConstants.PAR_DNAME, ""
	// + mTxtdoctor.getText().toString()));
	// HCClient.getInstance().request(this,
	// HCServerUtils.REQ_HOSPITAL_REVISIT, null, formData, null,
	// parser, new HCIRequestListener() {
	// @Override
	// public void onComplete(int req_type, int status) {
	// hideProgressDialog();
	// Intent intent = new Intent(HCRegistrationActivity.this,
	// HCPayActivity.class);
	// startActivity(intent);
	// }
	// });
	// }

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(0, android.R.anim.fade_out);
	}

}