package com.mavericks.checkin.parser;


public abstract class HCVisitParser extends HCBaseJsonParser {


	public static final String SESSION = "session";
	public static final String USER_ID = "user_id";
	public static final String TR_ID = "transaction_id";
//	HCHospitalHolder visitholder;
//	public HCVisitParser() {
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.viacom18.spotlight.models.STAJsonDataModel#initialize(java.lang.
//	 * StringBuilder)
//	 */
//	@Override
//	public void initialize(StringBuilder document) throws IOException,
//			JSONException {
//		super.initialize(document);
//		JSONObject obj = new JSONObject(document.toString());
//		if (obj.has(SESSION)) {
//			obj = obj.getJSONObject(SESSION);
//			visitholder = new HCHospitalHolder();
//			if (obj.has(USER_ID))
//				visitholder.setUser_id(obj.getString(USER_ID));
//			if (obj.has(TR_ID))
//				visitholder.setTransaction_id(obj.getString(TR_ID));
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getModelsCount()
//	 */
//	@Override
//	public int getModelsCount() {
//		return 0;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolderAt(int)
//	 */
//	@Override
//	public HCBaseHolder getDataHolderAt(int position) {
//		return null;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolder()
//	 */
//	@Override
//	public HCBaseHolder getDataHolder() {
//		return visitholder;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataList()
//	 */
//	@Override
//	public List<? extends HCBaseHolder> getDataList() {
//		return null;
//	}

}
