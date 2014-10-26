package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCSpinnerAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.holders.HCNameValuePair;
import com.mavericks.checkin.parser.HCGetHospitalParser;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCRetryDialog;
import com.mavericks.checkin.utils.HCRetryDialog.OnRetryClickListener;
import com.mavericks.checkin.utils.HCUtils;

public class HCHomeActivity extends HCBaseActivity implements OnClickListener {
	Button mBtnlocation;
	Button mBtnhospital;
	ImageView mImginfo;
	TextView mTxtproceed;
	ArrayList<HCLocationHolder> mHospitalList;
	HCSpinnerAdapter mAdapter;
	Map<String, ArrayList<HCLocationHolder>> mMapHospList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mBtnlocation = (Button) findViewById(R.id.btn_sel_loc);
		mBtnhospital = (Button) findViewById(R.id.btn_sel_hos);
		mTxtproceed = (TextView) findViewById(R.id.text_proceed);
		mImginfo = (ImageView) findViewById(R.id.img_info);

		// set listenr
		mImginfo.setOnClickListener(this);
		mTxtproceed.setOnClickListener(this);
		mBtnlocation.setOnClickListener(this);
		mBtnhospital.setOnClickListener(this);
		mBtnhospital.setEnabled(false);

		mAdapter = new HCSpinnerAdapter(this);
		mHospitalList = new ArrayList<HCLocationHolder>();
		mMapHospList = new HashMap<String, ArrayList<HCLocationHolder>>();

		// fetch Location details from server
		getLocation();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_proceed:
			Intent intent = new Intent(HCHomeActivity.this,
					HCRegistrationActivity.class);
			intent.putExtra(HCConstants.EXTRA_CONTENT_LIST, mHospitalList);
			startActivity(intent);
			finish();
			break;
		case R.id.img_info:
			intent = new Intent(HCHomeActivity.this, HCAboutUsActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.btn_sel_loc:
			showLocation();
			break;

		case R.id.btn_sel_hos:
			showHospital();
			break;
		default:
			break;
		}
	}

	/*
	 * Fetched the Location details from the server
	 */
	private void getLocation() {
		showProgressDialog("", false);

		final HCGetHospitalParser parser = new HCGetHospitalParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_HOSPITAL,
				null, null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mMapHospList = parser.getMapLocation();
							mHospitalList = (ArrayList<HCLocationHolder>) parser
									.getDataList();
						} else {
							// If we have data in database
							HCRetryDialog dialog = new HCRetryDialog(
									HCHomeActivity.this,
									HCServerUtils.REQ_GET_HOSPITAL);
							dialog.setOnRetryClickListener(new OnRetryClickListener() {

								@Override
								public void onRetryClick(int requestType) {
									getLocation();
								}
							});
							dialog.show(HCHomeActivity.this
									.getSupportFragmentManager(),
									HCHomeActivity.class.getSimpleName());
						}
					}
				});
	}

	/**
	 * shows the list of Location
	 */
	private void showLocation() {
		ArrayList<String> locationList = new ArrayList<String>();
		for (String key : mMapHospList.keySet()) {
			locationList.add(key);
		}

		mAdapter.setData(locationList);
		mAdapter.notifyDataSetChanged();
		new AlertDialog.Builder(this).setTitle("Select Location")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						HCUtils.Log(" On Clickk : "
								+ mAdapter.getItem(position).toString());
						mBtnlocation.setText(mAdapter.getItem(position)
								.toString());
						mBtnhospital.setEnabled(true);
						dialog.dismiss();
					}
				}).create().show();
	}

	/**
	 * shows the list of Hospital
	 */
	private void showHospital() {

		mAdapter.setData(mMapHospList.get(mBtnlocation.getText()));
		mAdapter.notifyDataSetChanged();

		new AlertDialog.Builder(this).setTitle("Select Hospital")
				.setAdapter(mAdapter, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int position) {
						HCLocationHolder hospital = (HCLocationHolder) mAdapter
								.getItem(position);
						mBtnhospital.setText(hospital.getmHosName());
						dialog.dismiss();
					}
				}).create().show();
	}

}