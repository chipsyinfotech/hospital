package com.mavericks.checkin.client;

import android.content.Context;

import com.mavericks.checkin.holders.HCProfileHolder;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

public class HCSession {

	private static HCSession mSession;

	public static HCSession getInstance() {
		if (mSession == null)
			mSession = new HCSession();

		return mSession;
	}

	public void storeSession(Context c, HCProfileHolder holder) {
		if (holder == null)
			return;

		HCUtils.editPrefString(HCConstants.PREF_PHONE, holder.getPhone(), c);
		HCUtils.editPrefString(HCConstants.PREF_PIC, holder.getPic(), c);
		HCUtils.editPrefString(HCConstants.PREF_USRID, holder.getUser_id(), c);
		HCUtils.editPrefString(HCConstants.PREF_USRNAME, holder.getName(), c);
		HCUtils.editPrefString(HCConstants.PREF_EMAIL, holder.getEmail(), c);
	}

	

	public String getFbId(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_FBID, "", c);
	}
	public String getEmailId(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_EMAIL, "", c);
	}

	public String getTwId(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_TWID, "", c);
	}

	public String getPhone(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_PHONE, "", c);
	}

	public String getPic(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_PIC, "", c);
	}

	public String getUser_id(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_USRID, "", c);
	}

	public String getUsrNm(Context c) {
		return HCUtils.getPrefString(HCConstants.PREF_USRNAME, "", c);
	}

	public void clearSession(Context c) {
		
		HCUtils.removePreference(HCConstants.PREF_FBID, c);
		HCUtils.removePreference(HCConstants.PREF_PHONE, c);
		HCUtils.removePreference(HCConstants.PREF_PIC, c);
		HCUtils.removePreference(HCConstants.PREF_TWID, c);
		HCUtils.removePreference(HCConstants.PREF_USRID, c);
		HCUtils.removePreference(HCConstants.PREF_USRNAME, c);
		HCUtils.removePreference(HCConstants.PREF_EMAIL, c);

	}
}
