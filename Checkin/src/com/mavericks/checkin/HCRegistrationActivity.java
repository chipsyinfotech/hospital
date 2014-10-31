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

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCSpinnerAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.client.HCSession;
import com.mavericks.checkin.holders.HCAmountHolder;
import com.mavericks.checkin.holders.HCDateHolder;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.holders.HCNameValuePair;
import com.mavericks.checkin.holders.HCSpecializationHolder;
import com.mavericks.checkin.holders.HCTimeHolder;
import com.mavericks.checkin.parser.HCDateParser;
import com.mavericks.checkin.parser.HCGetSpecializationParser;
import com.mavericks.checkin.parser.HCGetTimeParser;
import com.mavericks.checkin.parser.HCVisitParser;
import com.mavericks.checkin.utils.HCAlertManager;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCRetryDialog;
import com.mavericks.checkin.utils.HCRetryDialog.OnRetryClickListener;
//import com.mavericks.checkin.holders.HCAmountHolder;

public class HCRegistrationActivity extends HCBaseActivity implements
		OnClickListener {

	TextView mTxtlog;
	Button mBtnhospital;
	Button mBtnspecial;;
	Button mBtndate;;
	RadioGroup mRadgroup;
	RadioButton mRadyes;
	RadioButton mRadno;
	RadioButton mRadmale;
	RadioButton mRadfemale;
	Button mBtntime;;
	ImageView mImgdoctor;
	LinearLayout mLinvisit;
	LinearLayout mLinrevisit;
	CheckBox mCheckbox;
	RadioGroup mRadVisrevis;
	RadioButton mRadvisit;
	TextView mTxthospital;
	RadioButton mRadrevisit;
	EditText mEdtdoctor;
	EditText mEdtoccupation;
	Spinner mspinreligion;
	LinearLayout mLinDoctor;
	EditText mEdtnewfname;
	EditText mEdtfname;
	EditText mEdtage;
	EditText mEdthnumber;
	EditText mEdtmobile;
	EditText mEdtemail;
	EditText mEdtaddress;
	EditText mEdtmarried;
	EditText mEdtlocation;
	TextView mTxtdate;
	TextView mTxtnewcheck;
	EditText mEdtrefname;
	EditText mEdtmother;
	EditText mEdtrehnumber;
	EditText mEdtremobile;
	String married = null;
	String appointment = null;
	String userid = null;
	LinearLayout mLinmarriage;
	ImageView mImgmarriage;
	String lname = null;
	String mname = null;
	ImageView mImgmenu;
	String mAmount = null;
	String mVisit = null;
	String gender = null;
	String pass;
	String cash;
	String place;
	String occupation = "abc";
	String religion = "mm";
	String total_amount;
	String hospitalnumber;
	EditText mEdtremail;
	TextView mTxtrecheck;
	HCSpinnerAdapter mAdapter;
	HCTimeHolder mTimeHolder;
	 HCAmountHolder mAmountHolder;
	HCLocationHolder mHolder;
	EditText mEdtnewlname;
	EditText mEdtrelname;
	ArrayList<HCSpecializationHolder> mHosspecialization;
	ArrayList<HCLocationHolder> mHospitalList;
	ArrayList<HCTimeHolder> mHostime;
	ArrayList<HCDateHolder> mHosdate;
	String mHospitalId;
	String mSpecializtionId;
	String mVisitDate;
	String mTime;
	ArrayAdapter<String> Adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey(HCConstants.EXTRA_CONTENT_LIST)) {
			mHospitalList = bundle
					.getParcelableArrayList(HCConstants.EXTRA_CONTENT_LIST);
		}
		setContentView(R.layout.activity_hcregistration);

		// initialize the UI
		initUI();

		// Fetch
	}

	/**
	 * initializes the UI
	 */
	private void initUI() {

		mTxtlog = (TextView) findViewById(R.id.text_log);
		mRadgroup = (RadioGroup) findViewById(R.id.radio_app);
		mRadyes = (RadioButton) findViewById(R.id.radio_yes);

		mRadno = (RadioButton) findViewById(R.id.radio_no);
		mImgdoctor = (ImageView) findViewById(R.id.tab_doctor);
		mLinDoctor = (LinearLayout) findViewById(R.id.lin_doctor);
		mBtnhospital = (Button) findViewById(R.id.btn_sel_hos);
		mBtnspecial = (Button) findViewById(R.id.btn_sel_special);
		mBtndate = (Button) findViewById(R.id.btn_sel_date);
		mBtntime = (Button) findViewById(R.id.btn_sel_time);
		mRadmale=(RadioButton)findViewById(R.id.radio_male);
		mRadfemale=(RadioButton)findViewById(R.id.radio_female);
		mEdtnewfname = (EditText) findViewById(R.id.edt_pfname);
		mEdtnewlname = (EditText) findViewById(R.id.edt_lname);
		mEdtfname = (EditText) findViewById(R.id.edt_fname);
		mEdtmother = (EditText) findViewById(R.id.edt_mname);
		mEdtage = (EditText) findViewById(R.id.edt_age);
		mTxthospital = (TextView) findViewById(R.id.text_hospital);
		mImgmenu = (ImageView) findViewById(R.id.img_menu);
		mEdtmobile = (EditText) findViewById(R.id.edt_mobile);
		mEdtmarried = (EditText) findViewById(R.id.edt_married);
		mEdtemail = (EditText) findViewById(R.id.edt_email);
		mCheckbox = (CheckBox) findViewById(R.id.check_box);
		mEdtoccupation = (EditText) findViewById(R.id.edt_occupation);
		mspinreligion = (Spinner) findViewById(R.id.spinner_religion);
		mEdtaddress = (EditText) findViewById(R.id.edt_address);
	
		mTxtnewcheck = (TextView) findViewById(R.id.text_checkin);
		mEdtrefname = (EditText) findViewById(R.id.edt_refname);
		mEdtrelname = (EditText) findViewById(R.id.edt_relname);
		mEdtrehnumber = (EditText) findViewById(R.id.edt_rhnumber);
		mEdtremobile = (EditText) findViewById(R.id.edt_rmobile);
		mTxtrecheck = (TextView) findViewById(R.id.text_recheckin);
		mLinmarriage = (LinearLayout) findViewById(R.id.lin_marriage);
		mImgmarriage = (ImageView) findViewById(R.id.tab_married);
		mLinvisit = (LinearLayout) findViewById(R.id.lin_visit);
		mRadVisrevis = (RadioGroup) findViewById(R.id.radio_visre);
		mRadvisit = (RadioButton) findViewById(R.id.radio_visit);
		mRadrevisit = (RadioButton) findViewById(R.id.radio_revisit);
		mLinrevisit = (LinearLayout) findViewById(R.id.lin_revisit);
		mEdtdoctor = (EditText) findViewById(R.id.edt_doctor);
		// set listener

		mBtnhospital.setOnClickListener(this);
		mRadyes.setOnClickListener(this);
		mRadno.setOnClickListener(this);
		mRadvisit.setOnClickListener(this);
		mBtndate.setOnClickListener(this);
		mBtntime.setOnClickListener(this);
		mTxthospital.setOnClickListener(this);
		mImgmenu.setOnClickListener(this);
		mBtnspecial.setOnClickListener(this);
		mRadrevisit.setOnClickListener(this);
		mTxtnewcheck.setOnClickListener(this);
		mTxtrecheck.setOnClickListener(this);
		mLinDoctor.setVisibility(View.GONE);
		mImgdoctor.setVisibility(View.GONE);
		mTxtlog.setOnClickListener(this);
		mLinrevisit.setVisibility(View.GONE);
		mAdapter = new HCSpinnerAdapter(this);
		mLinmarriage.setVisibility(View.GONE);
		mImgmarriage.setVisibility(View.GONE);
		mBtndate.setEnabled(false);
		mHostime = new ArrayList<HCTimeHolder>();
		mCheckbox.setOnClickListener(this);
		mBtnspecial.setEnabled(false);
		mBtntime.setEnabled(false);

		findViewById(R.id.text_recheckin).setEnabled(false);
		findViewById(R.id.text_checkin).setEnabled(false);
	}

	private void resetUi(int id) {
		if (id == mBtnhospital.getId()) {
			mBtnspecial.setText(getString(R.string.special_prompt));
			mBtndate.setText(getString(R.string.date_prompt));
			mBtntime.setText(getString(R.string.time_prompt));

			mSpecializtionId = null;
			mVisitDate = null;

			mBtndate.setEnabled(false);
			mBtntime.setEnabled(false);
		}

		else if (id == mBtnspecial.getId()) {
			mBtndate.setText(getString(R.string.date_prompt));
			mBtntime.setText(getString(R.string.time_prompt));

			mVisitDate = null;

			mBtntime.setEnabled(false);
		}
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
		case R.id.check_box:
			if ((mCheckbox.isChecked())) {

				mLinmarriage.findViewById(R.id.lin_marriage).setVisibility(
						View.VISIBLE);
				mImgmarriage.findViewById(R.id.tab_married).setVisibility(
						View.VISIBLE);

			} else {

				mLinmarriage.findViewById(R.id.lin_marriage).setVisibility(
						View.GONE);
				mImgmarriage.findViewById(R.id.tab_married).setVisibility(
						View.GONE);
			}
			break;

		case R.id.btn_sel_special:
			showSpecialization();
			break;
		case R.id.btn_sel_date:
			showDate();

			break;
		case R.id.img_menu:
			startActivity(new Intent(HCRegistrationActivity.this,
					HCHistoryActivity.class));
			overridePendingTransition(R.anim.anim_slide_in_left, 0);
			break;

		case R.id.btn_sel_time:
			showTime();
			break;
		case R.id.btn_sel_hos:
			showHospital();
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
			// Log in true and Form valid true --- > New visit
			// Log in true and Form valid false --- > Dialog Display
			// Log in false and Form valid true --- > show login
			// Log in false and form valid false --- > first login then valid

			if ((HCSession.getInstance().isLoggedIn(
					HCRegistrationActivity.this, HCConstants.PREF_USRID))) {
				// true
				if (isFormNewValid()) {
					newvisit();

				}
			} else {
				Intent intent = new Intent();
				intent.setClass(HCRegistrationActivity.this,
						HCSignInActivity.class);
				startActivity(intent);
			}

			break;
		case R.id.text_hospital:
			startActivity(new Intent(HCRegistrationActivity.this,
					HCHomeActivity.class));
			overridePendingTransition(R.anim.anim_slide_in_left, 0);
			break;

		case R.id.text_log:

			startActivity(new Intent(HCRegistrationActivity.this,
					HCSignInActivity.class));
			overridePendingTransition(R.anim.slide_in_top_scr, 0);
			break;
		case R.id.text_recheckin:

			if ((HCSession.getInstance().isLoggedIn(
					HCRegistrationActivity.this, HCConstants.PREF_USRID))) {
				// true
				if (isFormRevisitValid()) {
					revisit();

				}
			} else {
				Intent intent = new Intent();
				intent.setClass(HCRegistrationActivity.this,
						HCSignInActivity.class);
				startActivity(intent);
			}

			break;
		default:
			break;
		}

	}

	private void getSpecialization() {
		showProgressDialog("", false);

		final HCGetSpecializationParser parser = new HCGetSpecializationParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		formData.add(new HCNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				mHospitalId));
		HCClient.getInstance().request(this,
				HCServerUtils.REQ_GET_SPECIALIZATION, null, null, formData,
				parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHosspecialization = (ArrayList<HCSpecializationHolder>) parser
									.getDataList();
							mBtnspecial.setEnabled(true);
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

	/**
	 * shows the list of Hospitals
	 */
	private void showHospital() {

		mAdapter.setData(mHospitalList);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select Hospital")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						resetUi(mBtnhospital.getId());
						HCLocationHolder location = (HCLocationHolder) mAdapter
								.getItem(position);
						mBtnhospital.setText(location.getmHosName());
						mHospitalId = location.getmHosId();
						getSpecialization();

						dialog.dismiss();
					}
				}).create().show();
	}

	/**
	 * shows list of specialization
	 */
	private void showSpecialization() {

		mAdapter.setData(mHosspecialization);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select specialization")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						resetUi(mBtnspecial.getId());
						HCSpecializationHolder special = (HCSpecializationHolder) mAdapter
								.getItem(position);
						mBtnspecial.setText(special.getmHosSpecializationname());

						mSpecializtionId = special.getmHosSpecialdetail();
						getDate();
						dialog.dismiss();
					}
				}).create().show();
	}

	private void getDate() {
		showProgressDialog("", false);

		final HCDateParser parser = new HCDateParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		formData.add(new HCNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				mHospitalId));

		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_DATE, null,
				null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHosdate = (ArrayList<HCDateHolder>) parser
									.getDataList();
							mBtndate.setEnabled(true);
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
						mVisitDate = date.getDate();
						getTime();
						dialog.dismiss();
					}
				}).create().show();
	}

	private void getTime() {
		showProgressDialog("", false);

		final HCGetTimeParser parser = new HCGetTimeParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		formData.add(new HCNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				mHospitalId));
		formData.add(new HCNameValuePair(HCConstants.PAR_SPECIAL_ID,
				mSpecializtionId));
		formData.add(new HCNameValuePair(HCConstants.PAR_VISIT_DATE, mVisitDate));
		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_TIME, null,
				null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mHostime = (ArrayList<HCTimeHolder>) parser
									.getDataList();
							mAmountHolder = parser.getAmountDetails();
							mBtntime.setEnabled(true);
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
						mBtntime.setText(time.getAppointtime()+" "+ "(Availabile " +time.getAvailability()+")");
						mTime = time.getTime_id();
						mTimeHolder = (HCTimeHolder) mAdapter.getItem(position);
						findViewById(R.id.text_recheckin).setEnabled(true);
						findViewById(R.id.text_checkin).setEnabled(true);
						dialog.dismiss();
					}
				}).create().show();
	}

	private boolean isFormNewValid() {
		int msg = 0;
		boolean bValid = true;
		if (mEdtnewfname.getText().toString().trim().length() == 0) {
			msg = R.string.err_firstname;
			bValid = false;
		}
		else if (mEdtnewlname.getText().toString().trim().length() == 0) {
			msg = R.string.err_lastname;
			bValid = false;
		}else if (mEdtfname.getText().toString().trim().length() == 0) {
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
		}
			 else if (mEdtoccupation.getText().toString().trim().length() == 0) {
					msg = R.string.err_occupation;
					bValid = false;
				}
		else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
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
		if (mEdtrefname.getText().toString().trim().length() == 0) {
			msg = R.string.err_firstname;
			bValid = false;
		}
		else if (mEdtrelname.getText().toString().trim().length() == 0) {
			msg = R.string.err_lastname;
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

	/* newvisit */
	private void newvisit() {
		String visittype = "1";
		String gender="Male";
		String prioritypass = "1";
		if (mRadvisit.isChecked())

			visittype = "1";

		else if (mRadrevisit.isChecked())

			visittype = "0";

		if (mRadyes.isChecked())
			prioritypass = "1";

		else if (mRadno.isChecked())
			prioritypass = "0";
		
		if (mRadmale.isChecked())
			prioritypass = "Male";

		else if (mRadfemale.isChecked())
			prioritypass = "Female";

		ArrayList<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();

		formData.add(new HCNameValuePair(HCConstants.PARAM_USERID, ""
				+ HCSession.getInstance().getUser_id(this)));

		formData.add(new HCNameValuePair(HCConstants.PAR_SPECIAL_ID,
				mSpecializtionId));
		formData.add(new HCNameValuePair(HCConstants.PAR_VISIT_DATE, mVisitDate));
		formData.add(new HCNameValuePair(HCConstants.PAR_TIME_ID, mTime));
		formData.add(new HCNameValuePair(HCConstants.PAR_FNAME, ""
				+ mEdtnewfname.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_LNAME, ""
				+ mEdtnewlname.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_MOB_NUMB, ""
				+ mEdtmobile.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				mHospitalId));
		formData.add(new HCNameValuePair(HCConstants.PAR_PASS, prioritypass));

		 formData.add(new HCNameValuePair(HCConstants.PAR_TOTAL_AMOUNT,
		 mAmountHolder
		 .getNewvisit_total_amount()));
		formData.add(new HCNameValuePair(HCConstants.PAR_VISIT_TYPE, visittype));

		formData.add(new HCNameValuePair(HCConstants.PAR_DNAME, ""
				+ mEdtdoctor.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_FANAME, ""
				+ mEdtfname.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_MOTHER, ""
				+ mEdtmother.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_AGE, ""
				+ mEdtage.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_GENDER, gender));
		formData.add(new HCNameValuePair(HCConstants.PAR_ADDRESS, ""
				+ mEdtaddress.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_PLACE, ""
				+ mEdtlocation.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_RELIGION, ""
				+ mspinreligion.getContext().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_OCCUPATION, ""
				+ mEdtoccupation.getText().toString()));

		formData.add(new HCNameValuePair(HCConstants.PAR_MARRIED, ""
				+ mEdtmarried.getText().toString()));

//		Bundle extra = new Bundle();
//		extra.putSerializable(HCConstants.EXTRA_CONTENT, formData);

		Intent intent = new Intent(getBaseContext(), HCPayActivity.class);
//		intent.putExtra(HCConstants.EXTRA_PASS, extra);
		intent.putParcelableArrayListExtra(HCConstants.EXTRA_PASS, formData);
		intent.putExtra(HCConstants.EXTRA_IS_NW_VISIT, true);
		 intent.putExtra(HCConstants.EXTRA_TIME, mAmountHolder);
		startActivity(intent);

	}

	/**
	 * revisit
	 */
	private void revisit() {
		String visittype = "1";
		String prioritypass = "1";
		if (mRadvisit.isChecked())
			visittype = "1";

		else if (mRadrevisit.isChecked())
			visittype = "0";

		if (mRadyes.isChecked())
			prioritypass = "1";

		else if (mRadno.isChecked())
			prioritypass = "0";

		final HCVisitParser parser = new HCVisitParser();
		ArrayList<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();

		formData.add(new HCNameValuePair(HCConstants.PARAM_USERID, ""
				+ HCSession.getInstance().getUser_id(this)));

		formData.add(new HCNameValuePair(HCConstants.PAR_SPECIAL_ID,
				mSpecializtionId));
		formData.add(new HCNameValuePair(HCConstants.PAR_VISIT_DATE, mVisitDate));
		formData.add(new HCNameValuePair(HCConstants.PAR_TIME_ID, mTime));
		formData.add(new HCNameValuePair(HCConstants.PAR_FNAME, ""
				+ mEdtrefname.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_LNAME, ""
				+ mEdtrelname.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_MOB_NUMB, ""
				+ mEdtmobile.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_HOSPITAL_ID,
				mHospitalId));
		formData.add(new HCNameValuePair(HCConstants.PAR_PASS, prioritypass));

		 formData.add(new HCNameValuePair(HCConstants.PAR_TOTAL_AMOUNT,
		 mAmountHolder
		 .getRevisit_total_amount()));
		formData.add(new HCNameValuePair(HCConstants.PAR_VISIT_TYPE, visittype));
		formData.add(new HCNameValuePair(HCConstants.PAR_CASH_ON_ARRIVAL, cash));
		formData.add(new HCNameValuePair(HCConstants.PAR_DNAME, ""
				+ mEdtdoctor.getText().toString()));
		formData.add(new HCNameValuePair(HCConstants.PAR_HNUMBER,
				hospitalnumber));

//		Bundle extra = new Bundle();
//		extra.putSerializable(HCConstants.EXTRA_CONTENT, formData);

		Intent intent = new Intent(getBaseContext(), HCPayActivity.class);
		intent.putParcelableArrayListExtra(HCConstants.EXTRA_PASS, formData);
		intent.putExtra(HCConstants.EXTRA_IS_NW_VISIT, false);
		intent.putExtra(HCConstants.EXTRA_TIME, mAmountHolder);
		startActivity(intent);

	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();

		overridePendingTransition(0, android.R.anim.fade_out);
	}

}