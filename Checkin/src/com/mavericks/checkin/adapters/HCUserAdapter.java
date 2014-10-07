package com.mavericks.checkin.adapters;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mavericks.checkin.R;
import com.mavericks.checkin.holders.HCHistoryHolder;

@SuppressLint("InflateParams") public class HCUserAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater mInflater;
	ViewHolder mViewHolder;
	ArrayList<HCHistoryHolder> mList;
	private static class ViewHolder {
		TextView mTxttime;
		TextView mTxtdate;
		ImageView mImgPic;
	}

	public HCUserAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(ArrayList<HCHistoryHolder> list) {
		mList = list;
	}
	
	@Override
	public int getCount() {
		return 6;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.user_list, null);

			holder = new ViewHolder();

			holder.mImgPic = (ImageView) convertView.findViewById(R.id.img);

			holder.mTxttime = (TextView) convertView.findViewById(R.id.txt_time);
			holder.mTxtdate = (TextView) convertView.findViewById(R.id.txt_date);
			

			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.mImgPic.setImageResource(R.drawable.recent_visits_icon);
		holder.mTxttime.setText("booked for " +mList.get(position).getName()+"at"+mList.get(position).getVisit_date());
		holder.mTxtdate.setText(mList.get(position).getVisit_date());
		return convertView;
	}

	@Override
	public HCHistoryHolder getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}



}
