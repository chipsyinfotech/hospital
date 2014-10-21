package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCGetHospitalParser extends HCBaseJsonParser {

	public static final String DATA = "data";
	public static final String HOSPITAL_MASTER = "hospital_master";
	public static final String HOSPITAL_LOCATION = "hospital_location";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String HOSPITAL_NAME = "hospital_name";
	public static final String HOSPITAL_ADDRESS = "hospital_address";
	public static final String HOSPITAL_MOBILE = "hospital_mobile";
	public static final String HOSPITAL_PRIORPASS = "priority_pass";
	public static final String HOSPITAL_CASH_ARR = "cash_on_arrival";
	ArrayList<HCLocationHolder> mHolderList;
	public HCGetHospitalParser() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#initialize(java.lang.
	 * StringBuilder)
	 */
	@Override
	public void initialize(StringBuilder document) throws IOException,
			JSONException {
		super.initialize(document);
		
		mHolderList= new ArrayList<HCLocationHolder>();
		
		JSONObject obj = new JSONObject(document.toString());
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);
			if (data.has(HOSPITAL_MASTER)) {
				JSONArray master = data.getJSONArray(HOSPITAL_MASTER);
				for (int i = 0; i < master.length(); i++) {

					JSONObject mcontent = master.getJSONObject(i);

					HCLocationHolder holder = new HCLocationHolder();

					if (!mcontent.isNull(HOSPITAL_ID)
							&& mcontent.has(HOSPITAL_ID)) {
						holder.setmHosId(mcontent.getString(HOSPITAL_ID));
					}

					if (!mcontent.isNull(HOSPITAL_NAME)
							&& mcontent.has(HOSPITAL_NAME)) {
						holder.setmHosName(mcontent.getString(HOSPITAL_NAME));
					}
					if (!mcontent.isNull(HOSPITAL_ADDRESS)
							&& mcontent.has(HOSPITAL_ADDRESS)) {
						holder.setmHosAddr(mcontent.getString(HOSPITAL_ADDRESS));
					}
					if (!mcontent.isNull(HOSPITAL_MOBILE)
							&& mcontent.has(HOSPITAL_MOBILE)) {
						holder.setmHosMobile(mcontent.getString(HOSPITAL_MOBILE));
					}

					if (!mcontent.isNull(HOSPITAL_CASH_ARR)
							&& mcontent.has(HOSPITAL_CASH_ARR)) {
						holder.setmCashOnArrival(mcontent.getInt(HOSPITAL_CASH_ARR));
					}
					
					if (!mcontent.isNull(HOSPITAL_PRIORPASS)
							&& mcontent.has(HOSPITAL_PRIORPASS)) {
						holder.setmPriorityPass(mcontent.getInt(HOSPITAL_PRIORPASS));
					}
					
					if (!mcontent.isNull(HOSPITAL_LOCATION)
							&& mcontent.has(HOSPITAL_LOCATION)) {						
						holder.setmHosLocation(mcontent.getString(HOSPITAL_LOCATION));						
					}
					mHolderList.add(holder);
					
				}

			}

		}
	} /*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getModelsCount()
	 */

	@Override
	public int getModelsCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolderAt(int)
	 */
	@Override
	public HCBaseHolder getDataHolderAt(int position) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolder()
	 */
	@Override
	public HCBaseHolder getDataHolder() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataList()
	 */
	@Override
	public List<? extends HCBaseHolder> getDataList() {
		return mHolderList;
	}
	
	public Map<String,ArrayList<HCLocationHolder>> getMapLocation() {
		Map<String, ArrayList<HCLocationHolder>> mMap = new HashMap<String, ArrayList<HCLocationHolder>>();
		for(int i=0 ; i<mHolderList.size() ; i++) {
			String tag = mHolderList.get(i).getmHosLocation();
			if(mMap.containsKey(tag))
				continue;
			
			HCUtils.Log("TAG : "+tag);
			ArrayList<HCLocationHolder> list = new ArrayList<HCLocationHolder>();
			for(int j=i ; j<mHolderList.size() ; j++){					
				if(mHolderList.get(j).getmHosLocation().equals(tag)) {
					list.add(mHolderList.get(j));
					HCUtils.Log("j"+j+ "Val :"+mHolderList.get(j).getmHosName());
				}
			}
			
			mMap.put(tag, list);
			HCUtils.Log("i"+i+ "Size :"+list.size());
		}
		
		return mMap;
	}

}
