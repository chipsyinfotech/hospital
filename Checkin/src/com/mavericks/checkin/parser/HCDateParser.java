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
import com.mavericks.checkin.holders.HCDateHolder;
import com.mavericks.checkin.holders.HCLocationHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCDateParser extends HCBaseJsonParser {

	public static final String DATA = "data";
	public static final String DATE = "date";
	public static final String DATE_DETAIL = "date_details";
	ArrayList<HCDateHolder> mHolderList;
	public HCDateParser() {

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
		
		mHolderList= new ArrayList<HCDateHolder>();
		
		JSONObject obj = new JSONObject(document.toString());
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);
			if (data.has(DATE_DETAIL)) {
				JSONArray master = data.getJSONArray(DATE_DETAIL);
				for (int i = 0; i < master.length(); i++) {

					JSONObject mcontent = master.getJSONObject(i);

					HCDateHolder holder = new HCDateHolder();

					if (!mcontent.isNull(DATE)
							&& mcontent.has(DATE)) {
						holder.setDate(mcontent.getString(DATE));
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
	
	

}
