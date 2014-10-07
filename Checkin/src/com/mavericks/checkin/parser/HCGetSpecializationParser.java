package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCHospitalHolder;

public class HCGetSpecializationParser extends HCBaseJsonParser {
	public static final String SESSION = "session";
	public static final String DATA = "data";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String SPECIAL_DETAIL = "specilization_details";
	public static final String SPECIALIZATION = "specialization";
	public static final String SPECIAL_NAME = "specialization_name";
	public static final String DATE_DETAILS = "date_details";
	public static final String DATE_COUNT = "date_count";
	HCHospitalHolder holder;

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

					HCHospitalHolder holder = new HCHospitalHolder();

					if (!mcontent.isNull(SPECIALIZATION)
							&& mcontent.has(SPECIALIZATION)) {
						holder.setId(mcontent.getString(SPECIALIZATION));
					}

					if (!mcontent.isNull(SPECIAL_NAME)
							&& mcontent.has(SPECIAL_NAME)) {
						holder.setSp_name(mcontent.getString(SPECIAL_NAME));
					}

				}
			}
			if (data.has(DATE_DETAILS)) {
				JSONArray date = data.getJSONArray(DATE_DETAILS);

				for (int i = 0; i < date.length(); i++) {

					JSONObject count = date.getJSONObject(i);

					HCHospitalHolder holder = new HCHospitalHolder();

					if (!count.isNull(DATE_COUNT) && count.has(DATE_COUNT)) {
						holder.setDate_count(count.getString(DATE_COUNT));
					}

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
		return holder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataList()
	 */
	@Override
	public List<? extends HCBaseHolder> getDataList() {
		return null;
	}

}
