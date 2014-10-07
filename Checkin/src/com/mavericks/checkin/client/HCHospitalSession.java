package com.mavericks.checkin.client;

import android.content.Context;

import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.holders.HCHospitalHolder;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

public class HCHospitalSession {

	private static HCHospitalSession mSession;

	public static HCHospitalSession getInstance() {
		if (mSession == null)
			mSession = new HCHospitalSession();

		return mSession;
	}

	public void storeSession(Context c, HCHospitalHolder holder) {
		if (holder == null)
			return;

		
		HCUtils.editPrefString(HCConstants.PREF_TRID,
				holder.getTransaction_id(), c);
		HCUtils.editPrefString(HCConstants.PREF_HID, holder.getId(),
				c);
		HCUtils.editPrefString(HCConstants.PREF_NAME,
				holder.getName(), c);
		HCUtils.editPrefString(HCConstants.PREF_ADDRESS, holder.getAddress(),
				c);
		HCUtils.editPrefString(HCConstants.PREF_PHONE,
				holder.getPhone(), c);
		HCUtils.editPrefString(HCConstants.PREF_HGROUP,
				holder.getGroup(), c);
	}



	public String getTrId(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_TRID, "", c);
	}
	public String getHosId(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_HID, "", c);
	}
	public String getHosname(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_NAME, "", c);
	}
	public String getHosaddress(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_ADDRESS, "", c);
	}
	public String getPhone(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_PHONE, "", c);
	}
	public String getGroup(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_HGROUP, "", c);
	}
	public void clearSession(Context c) {

		
		HCUtils.removePreference(HCConstants.PREF_TRID, c);
		HCUtils.removePreference(HCConstants.PREF_NAME, c);
		HCUtils.removePreference(HCConstants.PREF_HID, c);
		HCUtils.removePreference(HCConstants.PREF_ADDRESS, c);
		HCUtils.removePreference(HCConstants.PREF_PHONE, c);
		HCUtils.removePreference(HCConstants.PREF_HGROUP, c);

	}
}
