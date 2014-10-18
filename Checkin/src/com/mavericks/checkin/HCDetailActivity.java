package com.mavericks.checkin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.mavericks.checkin.holders.HCHistoryHolder;
import com.mavericks.checkin.utils.HCConstants;

public class HCDetailActivity extends Activity {
	TextView mTxttid;
	TextView mTxtpname;
	TextView mTxthname;
	TextView mTxtspecial;
	TextView mTxtaddress;
	TextView mTxtvisit;
	TextView mTxtpaid;
	TextView mTxtpass;
	TextView mTxtbook;
	HCHistoryHolder mHolder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcdetail);
		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey(HCConstants.EXTRA_HISTORY_LIST)) {
			mHolder = bundle.getParcelable(HCConstants.EXTRA_HISTORY_LIST);
		}

		mTxttid = (TextView) findViewById(R.id.text_id);
		mTxttid.setText(mHolder.getTransaction_id());
		mTxtpname = (TextView) findViewById(R.id.text_pname);
		mTxtpname.setText(mHolder.getUser_name());
		mTxthname = (TextView) findViewById(R.id.text_hname);
		mTxthname.setText(mHolder.getName());
		mTxtspecial = (TextView) findViewById(R.id.text_sname);
		mTxtspecial.setText(mHolder.getSpecial());
		mTxtaddress = (TextView) findViewById(R.id.text_address);
		mTxtaddress.setText(mHolder.getAddress());
		mTxtvisit = (TextView) findViewById(R.id.text_vdate);
		mTxtvisit.setText(mHolder.getVisit_date());
		mTxtpaid = (TextView) findViewById(R.id.text_tpaid);
		mTxtpaid.setText(mHolder.getTotalamount());
		mTxtpass = (TextView) findViewById(R.id.text_priorpass);
		mTxtpass.setText(mHolder.getVisit_date());
		mTxtbook = (TextView) findViewById(R.id.text_bdate);
		mTxtbook.setText(mHolder.getDob());

	}

}
