package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCHospitalHolder;


public class HCGetHospitalParser extends HCBaseJsonParser {

	public static final String DATA = "data";
	public static final String HOSPITAL_MASTER = "hospital_master";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String HOSPITAL_NAME = "hospital_name";
	public static final String HOSPITAL_ADDRESS = "hospital_address";
	public static final String HOSPITAL_MOBILE = "hospital_mobile";
	public static final String HOSPITAL_GROUP = "hospital_group";
	HCHospitalHolder holder; 
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

		JSONObject obj = new JSONObject(document.toString());
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);

			if (data.has(HOSPITAL_MASTER)) {
				JSONArray master = data.getJSONArray(HOSPITAL_MASTER);

				for (int i = 0; i < master.length(); i++) {

					JSONObject mcontent = master.getJSONObject(i);

					HCHospitalHolder holder=new HCHospitalHolder();

					if (!mcontent.isNull(HOSPITAL_ID)
							&& mcontent.has(HOSPITAL_ID)) {
						holder.setId(mcontent.getString(HOSPITAL_ID));
					}

					if (!mcontent.isNull(HOSPITAL_NAME)
							&& mcontent.has(HOSPITAL_NAME)) {
						holder.setName(mcontent.getString(HOSPITAL_NAME));
					}
					if (!mcontent.isNull(HOSPITAL_ADDRESS)
							&& mcontent.has(HOSPITAL_ADDRESS)) {
						holder.setAddress(mcontent.getString(HOSPITAL_ADDRESS));
					}
					if (!mcontent.isNull(HOSPITAL_MOBILE)
							&& mcontent.has(HOSPITAL_MOBILE)) {
						holder.setPhone(mcontent.getString(HOSPITAL_MOBILE));
					}

					if (!mcontent.isNull(HOSPITAL_GROUP)
							&& mcontent.has(HOSPITAL_GROUP)) {
						holder.setGroup(mcontent.getString(HOSPITAL_GROUP));
					}		
				}
						
			}
	
		}
				}		/*
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
