package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.utils.HCUtils;

public class HCStatusParser extends HCBaseJsonParser {

	public static final String KEY = "header";
	public static final String TYPE = "Htype";
	public static final String NAME = "Hname";
	public static final String SESSION = "session";
	public static final String USER_ID = "user_id";
	public static final String UR_NM = "user_name";
	public static final String TR_ID = "transaction_id";
	public static final String IMG = "image_file";
	public static final String EMAIL = "email_address";
	HCProfileHolder holder;

	public HCStatusParser() {

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
			if (obj.has(USER_ID))
				holder.setUser_id(obj.getString(USER_ID));
			if (obj.has(EMAIL))
				holder.setEmail(obj.getString(EMAIL));

			HCUtils.Log(" ======== holder" + holder.getPic());
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
