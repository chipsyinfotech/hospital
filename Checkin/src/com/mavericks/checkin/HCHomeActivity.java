package com.mavericks.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mavericks.checkin.holders.HCHospitalHolder;

public class HCHomeActivity extends HCBaseActivity implements OnClickListener {
	Button mBtnlocation;
	Button mBtnhospital;
	ImageView mImginfo;
	HCHospitalHolder holder;
	ArrayAdapter<String> mAdapter;
	TextView mTxtproceed;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mAdapter = new ArrayAdapter<String>(this, R.layout.listiem_spinner);
		mAdapter.setDropDownViewResource(R.layout.listiem_spinner);
		mBtnlocation = (Button) findViewById(R.id.btn_location);
		mBtnhospital = (Button) findViewById(R.id.btn_hospital);
		mTxtproceed = (TextView) findViewById(R.id.text_proceed);
		mImginfo = (ImageView) findViewById(R.id.img_info);
		mImginfo.setOnClickListener(this);
		mBtnlocation.setOnClickListener(this);
		mBtnhospital.setOnClickListener(this);
		mTxtproceed.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_proceed:
			startActivity(new Intent(HCHomeActivity.this,
					HCRegistrationActivity.class));
		
			break;

		default:
			break;
		}
	}


}
