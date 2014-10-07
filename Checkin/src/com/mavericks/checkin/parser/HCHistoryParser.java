package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCHistoryHolder;

public class HCHistoryParser extends HCBaseJsonParser {
	public static final String SESSION = "session";
	public static final String USER_ID = "user_id";
	public static final String USER_HISTORY = "user_history";
	public static final String DATA = "data";
	public static final String TR_ID = "transaction_id";
	public static final String UR_NM = "user_name";
	public static final String DOB = "date_of_birth";
	public static final String GENDER = "gender";
	public static final String ADDRESS = "address";
	public static final String FATHER_NAME = "father_name";
	public static final String USER_HNUMBER = "user_hospital_no";
	public static final String EMAIL = "email_address";
	public static final String  MOBILE=  "mobile_number";	
	public static final String SPECIALIZATION =  "specilization";
	public static final String HOSPITAL_NAME = "hospital_name";
	public static final String VISIT_DATE = "visiting_date";
	public static final String DO_NAME = "doctor_name";
	public static final String TOTAL_AMOUNT = "total_amount";
	ArrayList<HCHistoryHolder> mHolderList;

	public HCHistoryParser() {

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
		mHolderList = new ArrayList<HCHistoryHolder>();
		JSONObject obj = new JSONObject(document.toString());
		String user_id = null;
		if (obj.has(SESSION)) {

			JSONObject jsonSession = obj.getJSONObject(SESSION);
			if (jsonSession.has(USER_ID))
				user_id = jsonSession.getString(USER_ID);
		}
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);

			if (data.has(USER_HISTORY)) {
				JSONArray detail = data.getJSONArray(USER_HISTORY);

				for (int i = 0; i < detail.length(); i++) {

					JSONObject mcontent = detail.getJSONObject(i);

					HCHistoryHolder holder = new HCHistoryHolder();

					if (!mcontent.isNull(TR_ID)
							&& mcontent.has(TR_ID)) {
						holder.setTransaction_id(mcontent.getString(TR_ID));
					}

					if (!mcontent.isNull(UR_NM)
							&& mcontent.has(UR_NM)) {
						holder.setUser_name(mcontent.getString(UR_NM));
					}
					if (!mcontent.isNull(DOB)
							&& mcontent.has(DOB)) {
						holder.setDob(mcontent.getString(DOB));
					}
					if (!mcontent.isNull(GENDER)
							&& mcontent.has(GENDER)) {
						holder.setGender(mcontent.getString(GENDER));
					}
					if (!mcontent.isNull(ADDRESS)
							&& mcontent.has(ADDRESS)) {
						holder.setAddress(mcontent.getString(ADDRESS));
					}
					if (!mcontent.isNull(FATHER_NAME)
							&& mcontent.has(FATHER_NAME)) {
						holder.setFname(mcontent.getString(FATHER_NAME));
					}
					if (!mcontent.isNull(USER_HNUMBER)
							&& mcontent.has(USER_HNUMBER)) {
						holder.setHnumber(mcontent.getString(USER_HNUMBER));
					}
					if (!mcontent.isNull(EMAIL)
							&& mcontent.has(EMAIL)) {
						holder.setEmail(mcontent.getString(EMAIL));
					}
					if (!mcontent.isNull(MOBILE)
							&& mcontent.has(MOBILE)) {
						holder.setMobile(mcontent.getString(MOBILE));
					}
					if (!mcontent.isNull(SPECIALIZATION)
							&& mcontent.has(SPECIALIZATION)) {
						holder.setSpecial(mcontent.getString(SPECIALIZATION));
					}
					if (!mcontent.isNull(HOSPITAL_NAME)
							&& mcontent.has(HOSPITAL_NAME)) {
						holder.setName(mcontent.getString(HOSPITAL_NAME));
					}
					if (!mcontent.isNull(VISIT_DATE)
							&& mcontent.has(VISIT_DATE)) {
						holder.setVisit_date(mcontent.getString(VISIT_DATE));
					}
					if (!mcontent.isNull(DO_NAME)
							&& mcontent.has(DO_NAME)) {
						holder.setDname(mcontent.getString(DO_NAME));
					}
					if (!mcontent.isNull(TOTAL_AMOUNT)
							&& mcontent.has(TOTAL_AMOUNT)) {
						holder.setTotalamount(mcontent.getString(TOTAL_AMOUNT));
					}
					mHolderList.add(holder);
				}
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
