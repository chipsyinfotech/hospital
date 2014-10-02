package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCHospitalSession;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCHospitalHolder;
import com.mavericks.checkin.parser.HCVisitParser;
import com.mavericks.checkin.utils.HCConstants;

public class HCPaymentActivity extends HCBaseActivity implements
		OnClickListener {

	private TextView pay;
	private TextView reg_fee;
	private TextView cons_fee;
	private TextView conv_fee;
	private TextView tot_amt;

	CheckBox pass;

	String amt_pay1;
	String c_charge1;
	String cc_charge1;
	String disc1;
	String ih_charge1;
	String total1;

	String amt_pay2;
	String c_charge2;
	String cc_charge2;
	String disc2;
	String ih_charge2;
	String total2;

	// revisit variables

	String mUserid;
	String specilisation;
	String selected_date;
	String t_id;

	EditText rname;
	String lname = null;
	EditText rmobile;
	EditText rhnumber;
	String h_id;
	String prioritypass;
	String priority_appoi;
	EditText doname;
	String remail;

	public void onCreate(Bundle savedInstanceState) {
		final Context context = this;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcpayment);

		amt_pay1 = HCRegistrationActivity.amt_pay;
		c_charge1 = HCRegistrationActivity.c_charge;
		cc_charge1 = HCRegistrationActivity.cc_charge;
		disc1 = HCRegistrationActivity.disc;
		ih_charge1 = HCRegistrationActivity.ih_charge;
		total1 = HCRegistrationActivity.total;

		amt_pay2 = HCRegistrationActivity.uamt_pay;
		c_charge2 = HCRegistrationActivity.uc_charge;
		cc_charge2 = HCRegistrationActivity.ucc_charge;
		disc2 = HCRegistrationActivity.udisc;
		ih_charge2 = HCRegistrationActivity.uih_charge;
		total2 = HCRegistrationActivity.utotal;

		// retrieving REVISIT string values

		mUserid = HCRegistrationActivity.mUserid;
		specilisation = HCRegistrationActivity.specilisation;
		selected_date = HCRegistrationActivity.selected_date;
		t_id = HCRegistrationActivity.t_id;

		rname = HCRegistrationActivity.rname;
		lname = HCRegistrationActivity.lname;
		rmobile = HCRegistrationActivity.rmobile;
		rhnumber = HCRegistrationActivity.rhnumber;
		h_id = HCRegistrationActivity.h_id;
		priority_appoi = HCRegistrationActivity.priority_appoi;
		doname = HCRegistrationActivity.doname;
		remail = HCRegistrationActivity.sremail;

		prioritypass = "0";

		reg_fee = (TextView) findViewById(R.id.text_rupees);
		cons_fee = (TextView) findViewById(R.id.text_rupee);
		conv_fee = (TextView) findViewById(R.id.text_rup);
		tot_amt = (TextView) findViewById(R.id.text_rupi);

		reg_fee.setText(amt_pay1);
		cons_fee.setText(c_charge1);
		conv_fee.setText(cc_charge1);
		tot_amt.setText(total1);

		pass = (CheckBox) findViewById(R.id.img_priority);

		pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (pass.isChecked()) {
					reg_fee.setText(amt_pay2);
					cons_fee.setText(c_charge2);
					conv_fee.setText(cc_charge2);
					tot_amt.setText(total2);
					prioritypass = "1";

				} else {

					reg_fee.setText(amt_pay1);
					cons_fee.setText(c_charge1);
					conv_fee.setText(cc_charge1);
					tot_amt.setText(total1);
					prioritypass = "0";

				}
			}
		});

		pay = (TextView) findViewById(R.id.text_payment);

		pay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				revisit();

				// Intent intent = new Intent(context, WebViewActivity.class);
				// startActivity(intent);
			}

		});

	}

	private void revisit() {
		showProgressDialog(null, false);
		final HCVisitParser parser = new HCVisitParser();

		Log.e("hospital id", "value of hospital id" + h_id);

		List<NameValuePair> formData = new ArrayList<NameValuePair>();

		// mUserid = HCRegistrationActivity.mUserid;
		// specilisation = HCRegistrationActivity.specilisation;
		// selected_date = HCRegistrationActivity.selected_date;
		// t_id = HCRegistrationActivity.t_id;
		// rname = HCRegistrationActivity.srname;
		// lname = HCRegistrationActivity.lname;
		// rmobile = HCRegistrationActivity.srmobile;
		// rhnumber = HCRegistrationActivity.srhnumber;
		// h_id = HCRegistrationActivity.h_id;
		// priority_appoi = HCRegistrationActivity.priority_appoi;
		// doname = HCRegistrationActivity.sdoname;
		// remail = HCRegistrationActivity.sremail;

		// formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID,
		// mUserid));
//
//		Log.e("userid...", "l" + "" + HCSession.getInstance().getUserId(this));
//		Log.e("h_id..", "l" + h_id);
//		Log.e("specia", "l" + specilisation);
//		Log.e("date", "l" + selected_date);
//		Log.e("t_id", "l" + t_id);
//		
//		Log.e("name", "l" + rname.getText().toString());
//		Log.e("last_name", "l" + lname);
//		Log.e("mobile", "l" + rmobile.getText().toString());
//		Log.e("hospital number", "l" + rhnumber.getText().toString());	
//		Log.e("priority pass", "l" + prioritypass);
//		Log.e("total_amount", "l" + tot_amt.getText().toString());
//		Log.e("priori appointmnt", "l" + priority_appoi);
//		Log.e("docotr name", "l" + doname.getText().toString());

		
		
//		formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID, ""
//				+ HCSession.getInstance().getUserId(this)));

		formData.add(new BasicNameValuePair(HCConstants.PAR_SPECIAL_ID,
				specilisation));
		formData.add(new BasicNameValuePair(HCConstants.PAR_VISIT_DATE,
				selected_date));
		formData.add(new BasicNameValuePair(HCConstants.PAR_TIME_ID, t_id));
		formData.add(new BasicNameValuePair(HCConstants.PAR_FNAME, ""
				+ rname.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_LNAME, lname));
		formData.add(new BasicNameValuePair(HCConstants.PAR_MOB_NUMB, ""
				+ rmobile.getText().toString()));

		formData.add(new BasicNameValuePair(HCConstants.PAR_HNUMBER, ""
				+ rhnumber.getText().toString()));
		formData.add(new BasicNameValuePair(HCConstants.PAR_HOSPITAL_ID, h_id));
		formData.add(new BasicNameValuePair(HCConstants.PAR_PASS, prioritypass));
//		formData.add(new BasicNameValuePair(HCConstants.PAR_AMT, ""
//				+ tot_amt.getText().toString()));

		formData.add(new BasicNameValuePair(HCConstants.PAR_APPOINTMENT,
				priority_appoi));
		formData.add(new BasicNameValuePair(HCConstants.PAR_DNAME, ""
				+ doname.getText().toString()));

		HCClient.getInstance().request(this,
				HCServerUtils.REQ_HOSPITAL_REVISIT, null, formData, null,
				parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();

						Toast.makeText(getApplicationContext(),
								"Record successfully inserted",
								Toast.LENGTH_SHORT).show();

						Intent intent = new Intent(HCPaymentActivity.this,
								WebViewActivity.class);
						startActivity(intent);

						HCHospitalSession.getInstance().storeSession(
								HCPaymentActivity.this,
								(HCHospitalHolder) parser.getDataHolder());

						finish();
					}
				});

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}