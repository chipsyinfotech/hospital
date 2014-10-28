package com.mavericks.checkin;

import android.os.Bundle;
import android.widget.TextView;

import com.mavericks.checkin.holders.HCTimeHolder;
import com.mavericks.checkin.utils.HCConstants;

public class HCPaymActivity extends HCBaseActivity {
	TextView mRegcharge;
	TextView mConsultcharge;
	TextView mConvencharge;
	TextView mDiscount;
	TextView mTotalamount;
	String regcharge;
	String muserid;
	String mhosid;
	String mtimeid;
	String mVisitDate;
	String mspecialid;
	String fname;
	String lname;
	String mobilenumber;
	String priorpass;
	String amount;
	String visittype;
	String doctorname;
	String fathername;
	String mothername;
	HCTimeHolder time;
	String age;
	String gender;
	String address;
	String place;
	String religion;
	String occuption;
	String marriage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		Bundle bundle = getIntent().getExtras();
//		if (bundle.containsKey(HCConstants.EXTRA_TIME)) {
//			time = bundle.getParcelable(HCConstants.EXTRA_HISTORY_LIST);
//		}
		setContentView(R.layout.activity_hcpaym);
		mRegcharge = (TextView) findViewById(R.id.text_regcharge);
	//	mRegcharge.setText(time.getNewvisit_general_amount());
		mConsultcharge = (TextView) findViewById(R.id.text_consltcharge);
	//	mConsultcharge.setText(time.getNewvisit_consulation_charges());
		mConvencharge = (TextView) findViewById(R.id.text_convcharge);
	//	mConvencharge.setText(time.getNewvisit_internet_charges());
		mDiscount = (TextView) findViewById(R.id.text_discountcharge);
	//	mDiscount.setText(time.getNewvisit_discount());
		mTotalamount = (TextView) findViewById(R.id.text_totamount);
	//	mTotalamount.setText(time.getNewvisit_total_amount());

//		Bundle extra = getIntent().getBundleExtra(HCConstants.EXTRA_PASS);
//		ArrayList<HCNameValuePair> formData = (ArrayList<HCNameValuePair>) extra
//				.getSerializable(HCConstants.EXTRA_CONTENT);
//
//		for (HCNameValuePair pair : formData) {
//			HCUtils.Log(" Key : " + pair.getName());
//			HCUtils.Log(" value : " + pair.getValue());
//
//			if (pair.getName().equals(HCConstants.PARAM_USERID)) {
//				muserid = pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_SPECIAL_ID)) {
//				mspecialid= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_VISIT_DATE)) {
//				mVisitDate= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_TIME_ID)) {
//				mtimeid= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_FNAME)) {
//				fname= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_LNAME)) {
//				lname= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_MOB_NUMB)) {
//				mobilenumber= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_HOSPITAL_ID)) {
//				mhosid= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_PASS)) {
//				priorpass= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_TOTAL_AMOUNT)) {
//				amount= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_VISIT_TYPE)) {
//				visittype= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_DNAME)) {
//				doctorname= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_FANAME)) {
//				fathername= pair.getValue();
//			}
//			
//			if (pair.getName().equals(HCConstants.PAR_MOTHER)) {
//				mothername= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_AGE)) {
//				age= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_GENDER)) {
//				gender= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_ADDRESS)) {
//				address= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_PLACE)) {
//				place= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_RELIGION)) {
//				religion= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_OCCUPATION)) {
//				occuption= pair.getValue();
//			}
//			if (pair.getName().equals(HCConstants.PAR_MARRIED)) {
//			marriage= pair.getValue();
//			}
//		}
	}

}
