package com.mavericks.checkin;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCHospitalAdapter;
import com.mavericks.checkin.holders.HCHospitalHolder;

public class HCHomeActivity extends HCBaseActivity implements OnClickListener,
		OnItemClickListener {
	Button mBtnlocation;
	Button mBtnhospital;
	ImageView mImginfo;
	TextView mTxtproceed;
	ArrayList<HCHospitalHolder> mholder;
	ArrayAdapter<String> mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		mBtnlocation = (Button) findViewById(R.id.btn_location);
		mBtnhospital = (Button) findViewById(R.id.btn_hospital);
		mTxtproceed = (TextView) findViewById(R.id.text_proceed);
		mImginfo = (ImageView) findViewById(R.id.img_info);
		mImginfo.setOnClickListener(this);
		mTxtproceed.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_proceed:
			Intent intent = new Intent(HCHomeActivity.this,
					HCRegistrationActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.img_info:
			Intent i = new Intent(HCHomeActivity.this, HCHistoryActivity.class);
			startActivity(i);
			finish();
			break;
		case R.id.btn_location:
			location();
			break;
		default:
			break;
		}
	}

	private void location() {
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}
}