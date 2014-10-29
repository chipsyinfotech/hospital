
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HUMMainMenuFragment.java
 * @Project:
 *		Hummer
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2012-2013, Viacom 18 Media Pvt. Ltd 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 06-Aug-2013
 */

package com.mavericks.checkin.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mavericks.checkin.HCDetailActivity;
import com.mavericks.checkin.R;
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

/**
 * @author vijayalaxmi
 *
 */
public class HCMainMenuFrag extends Fragment implements
OnItemClickListener,OnClickListener{
	View root;
	HCUserAdapter mAdapter;
	ListView list;
	HCHistoryHolder holder;
	TextView mTxt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 root = inflater.inflate(R.layout.activity_hchistory, null);
		 
		 mAdapter = new HCUserAdapter(getActivity());
			list = (ListView) root.findViewById(R.id.list_view);
			list.setAdapter(mAdapter);
			list.setOnItemClickListener(this);
			getHistory();
			
			return root;
	}
	
	private void getHistory() {

		HCProgressUtils.showProgressBar(getActivity(), root.findViewById(R.id.root),
				R.id.progressBar);
		final HCHistoryParser parser = new HCHistoryParser();
		List<HCNameValuePair> formData = new ArrayList<HCNameValuePair>();
		formData.add(new HCNameValuePair(HCConstants.PARAM_USERID, ""));
		HCClient.getInstance().request(getActivity(), HCServerUtils.REQ_GET_HISTORY,
				null, null, formData, parser, new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						HCProgressUtils.hideProgressBar(
								root.findViewById(R.id.root), R.id.progressBar);
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							mAdapter.setData((ArrayList<HCHistoryHolder>) parser
									.getDataList());
							mAdapter.notifyDataSetChanged();
						} else {
							// If we have data in database
							HCReturyUtils.showRetryFrame(
									root.findViewById(R.id.root),
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
		Intent intent = new Intent(getActivity(),
				HCDetailActivity.class);
		intent.putExtra(HCConstants.EXTRA_HISTORY_LIST, holder);
		startActivity(intent);
		
	}
	@Override
	public void onClick(View v) {

	}
}
