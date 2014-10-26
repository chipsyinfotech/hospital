package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCUserAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCHistoryHolder;
import com.mavericks.checkin.holders.HCNameValuePair;
import com.mavericks.checkin.parser.HCHistoryParser;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCProgressUtils;
import com.mavericks.checkin.utils.HCReturyUtils;

public class HCHistoryActivity extends HCBaseActivity implements
		OnItemClickListener,OnClickListener {
	HCUserAdapter mAdapter;
	ListView list;
	HCHistoryHolder holder;
	TextView mTxt;
	String userid = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hchistory);
		mAdapter = new HCUserAdapter(this);
		list = (ListView) findViewById(R.id.list_view);
		list.setAdapter(mAdapter);
		list.setOnItemClickListener(this);
		getHistory();
	}

	private void getHistory() {

		HCProgressUtils.showProgressBar(this, findViewById(R.id.root),
				R.id.progressBar);
		final HCHistoryParser parser = new HCHistoryParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		formData.add(new HCNameValuePair(HCConstants.PARAM_USERID, userid));
		HCClient.getInstance().request(this, HCServerUtils.REQ_GET_HISTORY,
				null, null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						HCProgressUtils.hideProgressBar(
								findViewById(R.id.root), R.id.progressBar);
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mAdapter.setData((ArrayList<HCHistoryHolder>) parser
									.getDataList());
							mAdapter.notifyDataSetChanged();
						} else {
							// If we have data in database
							HCReturyUtils.showRetryFrame(
									findViewById(R.id.root),
									new OnClickListener() {
										@Override
										public void onClick(View arg0) {
											getHistory();
										}
								}, HCServerUtils.REQ_GET_HISTORY);
						}
					}
				});
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		HCHistoryHolder holder = mAdapter.getItem(position);
		Intent intent = new Intent(HCHistoryActivity.this,
				HCDetailActivity.class);
		intent.putExtra(HCConstants.EXTRA_HISTORY_LIST, holder);
		startActivity(intent);
		finish();
	}
	@Override
	public void onClick(View v) {

	}

}
