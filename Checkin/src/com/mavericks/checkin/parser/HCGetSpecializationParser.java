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
import com.mavericks.checkin.holders.HCSpecializationHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCGetSpecializationParser extends HCBaseJsonParser {
	public static final String SESSION = "session";
	public static final String DATA = "data";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String SPECIAL_DETAIL = "specilization_details";
	public static final String SPECIALIZATION = "specialization ";
	public static final String SPECIAL_NAME = "specialization_name";
	public static final String DATE_DETAILS = "date_details";
	public static final String DATE_COUNT = "date_count";
	ArrayList<HCSpecializationHolder> mHolderList;

	public HCGetSpecializationParser() {

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
		mHolderList = new ArrayList<HCSpecializationHolder>();

		JSONObject obj = new JSONObject(document.toString());
		String hospital_id = null;
		if (obj.has(SESSION)) {

			JSONObject jsonSession = obj.getJSONObject(SESSION);
			if (jsonSession.has(HOSPITAL_ID))
				hospital_id = jsonSession.getString(HOSPITAL_ID);
		}
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);

			if (data.has(SPECIAL_DETAIL)) {
				JSONArray detail = data.getJSONArray(SPECIAL_DETAIL);

				for (int i = 0; i < detail.length(); i++) {

					JSONObject mcontent = detail.getJSONObject(i);

					HCSpecializationHolder holder = new HCSpecializationHolder();

					if (!mcontent.isNull(SPECIALIZATION)
							&& mcontent.has(SPECIALIZATION)) {
						holder.setmHosSpecialdetail(mcontent
								.getString(SPECIALIZATION));
					}

					if (!mcontent.isNull(SPECIAL_NAME)
							&& mcontent.has(SPECIAL_NAME)) {
						holder.setmHosSpecializationname(mcontent
								.getString(SPECIAL_NAME));
					}
					mHolderList.add(holder);
				}
				
				
			}

			
			
		}
	}

	/*
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
