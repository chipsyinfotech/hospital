package com.mavericks.checkin.adapters;

import java.util.ArrayList;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mavericks.checkin.R;
import com.mavericks.checkin.holders.HCHospitalHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCHospitalAdapter extends BaseAdapter {

	Context mContext;
	LayoutInflater mInflater;
	ViewHolder mViewHolder;
	ArrayList<HCHospitalHolder> mList;

	private static class ViewHolder {
	Button location;
	Button hospital;
	
	}

	public HCHospitalAdapter(Context context) {
		mContext = context;
		mInflater = (LayoutInflater) context
				.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
	}

	public void setData(ArrayList<HCHospitalHolder> list) {
		mList = list;
	}

	@Override
	public int getCount() {
		if (mList == null)
			return 0;
		else {
			HCUtils.Log("SIZE :::: " + mList.size());
			return mList.size();
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		if (convertView == null) {

			convertView = mInflater.inflate(R.layout.activity_home, null);

			holder = new ViewHolder();

			
			holder.location = (Button) convertView
					.findViewById(R.id.btn_location);

			holder.hospital = (Button) convertView
					.findViewById(R.id.btn_hospital);
			convertView.setTag(holder);
		} else

			holder = (ViewHolder) convertView.getTag();
		
		holder.location.setText(mList.get(position).getGroup());
		holder.hospital.setText(mList.get(position).getName());
		return convertView;
	}

	@Override
	public HCHospitalHolder getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	
}
