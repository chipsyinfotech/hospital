package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCTimeHolder;

public class HCGetTimeParser extends HCBaseJsonParser {
	public static final String SESSION = "session";
	public static final String DATA = "data";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String TIMING_DETAILS =  "timing_details";
	public static final String TIMING_ID =   "timing_id";
	public static final String APPOINTMENT_TIME ="appointment_timings";
	public static final String AVAILABILITY ="availability";
	public static final String AMOUNT_DETAILS ="amount_details";
	public static final String VISIT_TYPE ="visit_type";
	public static final String GENERAL_AMOUNT = "general_amount";
	
	
	ArrayList<HCTimeHolder> mHolderList;

	public HCGetTimeParser() {

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
		mHolderList = new ArrayList<HCTimeHolder>();
		JSONObject obj = new JSONObject(document.toString());
		String hospital_id = null;
		if (obj.has(SESSION)) {

			JSONObject jsonSession = obj.getJSONObject(SESSION);
			if (jsonSession.has(HOSPITAL_ID))
				hospital_id = jsonSession.getString(HOSPITAL_ID);
		}
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);

			if (data.has(TIMING_DETAILS)) {
				JSONArray detail = data.getJSONArray(TIMING_DETAILS);

				for (int i = 0; i < detail.length(); i++) {

					JSONObject mcontent = detail.getJSONObject(i);

					HCTimeHolder holder = new HCTimeHolder();

					if (!mcontent.isNull(TIMING_ID)
							&& mcontent.has(TIMING_ID)) {
						holder.setTime_id(mcontent.getString(TIMING_ID));
					}

					if (!mcontent.isNull(APPOINTMENT_TIME)
							&& mcontent.has(APPOINTMENT_TIME)) {
						holder.setAppointtime(mcontent.getString(APPOINTMENT_TIME));
					}
					if (!mcontent.isNull(AVAILABILITY)
							&& mcontent.has(AVAILABILITY)) {
						holder.setAvailability(mcontent.getString(AVAILABILITY));
					}
					
					mHolderList.add(holder);
				}
				
//				if (data.has(TIMING_DETAILS)) {
//					JSONArray detail = data.getJSONArray(TIMING_DETAILS);

			}
		}
	}/*
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
