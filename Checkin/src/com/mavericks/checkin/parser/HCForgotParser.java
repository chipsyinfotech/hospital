package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCForgotParser extends HCBaseJsonParser {

	
	public static final String SESSION = "session";

	public static final String EMAIL_ID = "email_address";


	HCProfileHolder holder;

	public HCForgotParser() {

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
		if (obj.has(SESSION)) {
			obj = obj.getJSONObject(SESSION);
			holder = new HCProfileHolder();
			if (obj.has(EMAIL_ID))
				holder.setEmail(obj.getString(EMAIL_ID));
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
