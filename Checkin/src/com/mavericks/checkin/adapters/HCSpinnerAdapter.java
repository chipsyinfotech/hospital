package com.mavericks.checkin.adapters;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mavericks.checkin.R;
import com.mavericks.checkin.holders.HCDateHolder;
import com.mavericks.checkin.holders.HCHistoryHolder;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.holders.HCSpecializationHolder;
import com.mavericks.checkin.holders.HCTimeHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCSpinnerAdapter extends BaseAdapter {
//
	public static final int TYPE_HOSPITAL = 100;
	public static final int TYPE_LOCATION = 101;
	public static final int TYPE_TIME = 102;
	public static final int TYPE_DATE = 103;
	public static final int TYPE_SPECIALIZATION = 104;
	Context mContext;
	LayoutInflater mInflater;
	ViewHolder mViewHolder;
	int mReqType;
	ArrayList<? extends Object> mHolderList;
	private static class ViewHolder {

		TextView mTxtData;
	}

	public HCSpinnerAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(ArrayList<? extends Object> list) {
		mHolderList = list;
	}

	public void setDataType(int type) {
		mReqType = type;
	}

	@Override
	public int getCount() {
		if (mHolderList == null)
			return 0;
		else {
			HCUtils.Log("SIZE :::: " + mHolderList.size());
			return mHolderList.size();
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder = null;

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.listitem_spinner, null);
			viewholder = new ViewHolder();
			viewholder.mTxtData = (TextView) convertView
					.findViewById(android.R.id.text1);

			convertView.setTag(viewholder);
		} else

			viewholder = (ViewHolder) convertView.getTag();

		Object dataHolder = mHolderList.get(position);
		if (dataHolder instanceof String) {
			viewholder.mTxtData.setText(dataHolder.toString());
		} else if (dataHolder instanceof HCLocationHolder) {
			viewholder.mTxtData.setText(((HCLocationHolder) dataHolder)
					.getmHosName());
		} else if (dataHolder instanceof HCSpecializationHolder) {
			viewholder.mTxtData.setText(((HCSpecializationHolder) dataHolder)
					.getmHosSpecializationname());
		}
		else if (dataHolder instanceof HCTimeHolder) {
			viewholder.mTxtData.setText((((HCTimeHolder) dataHolder)
					.getAppointtime())+" "+"(Available "+((HCTimeHolder) dataHolder)
					.getAvailability()+")");
		}
		else if (dataHolder instanceof HCDateHolder) {
			viewholder.mTxtData.setText(((HCDateHolder) dataHolder)
					.getDate());
		}

		return convertView;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return (Object) mHolderList.get(position);

	}

}
