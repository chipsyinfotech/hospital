package com.mavericks.checkin;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.mavericks.checkin.adapters.HCUserAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCHistoryHolder;
import com.mavericks.checkin.parser.HCHistoryParser;
import com.mavericks.checkin.utils.HCConstants;

public class HCHistoryActivity extends HCBaseActivity implements OnItemClickListener{
	HCUserAdapter mAdapter;
	ListView list;
	TextView mTxt;
String userid=null;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hchistory);
		mAdapter = new HCUserAdapter(this);
		list = (ListView) findViewById(R.id.list);
		list.setAdapter(mAdapter);
getHistory();
	}
	
	private void getHistory()
	{

			showProgressDialog(null, false);
			final HCHistoryParser parser = new HCHistoryParser();
			List<NameValuePair> formData = new ArrayList<NameValuePair>();
			formData.add(new BasicNameValuePair(HCConstants.PARAM_USERID,userid));
			HCClient.getInstance().request(this, HCServerUtils.REQ_GET_HISTORY,
					null, formData, null, parser, new HCIRequestListener() {
						@Override
						public void onComplete(int req_type, int status) {
							hideProgressDialog();
							if(status == HCConstants.ERROR_CODE_SUCCESS) {
							mAdapter.setData((ArrayList<HCHistoryHolder>) parser.getDataList());
								mAdapter.notifyDataSetChanged();
							}
						}
			});
								
			}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		
	}

	
}
