/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABBaseJsonParser.java
 * @Project:
 *		 Abharan
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */
package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.utils.HCUtils;


public abstract class HCBaseJsonParser {
	
    
	public static final int STATUS_ERR_ENCODING = 0 ;
	public static final int STATUS_NONETWORK = 1 ;
	
	public final static String KEY_STATUS = "status";
	public final static String KEY_STATUS_MSG = "massage";
	public final static String KEY_STATUS_ERROR = "code";
	public final static String KEY_COUNT = "count";	
	public final static String KEY_DATA = "data";
	public final static String KEY_KEYS= "keys";
	public final static String KEY_CELEBS = "celebs";
	public final static String KEY_USER = "user";
	public final static String KEY_SUMMARY = "summary";
	
	public final static int STATUS_CODE_SUCESS = 0;
	HCRequestStatus mStatus = new HCRequestStatus();	
	int mCount;

	/**
	 * Parses the JSON document and initializes fields of the DataModel. 
	 * @param document A JSON document received as a response from feeds.
	 * @throws IOException 
	 * @throws JSONException 
	 */
	
	public abstract int getModelsCount();  
	public abstract HCBaseHolder getDataHolderAt(int position);
	public abstract HCBaseHolder getDataHolder();
	public abstract List<? extends HCBaseHolder> getDataList();
	
	
	/**
	 * @param responce : Reponce from server 
	 * @throws IOException
	 * @throws JSONException
	 *  
	 * This methode reduce redundunt work
	 * Make usre you call supe.initiate from sub class
	 */
	public void initialize(StringBuilder responce) throws IOException, JSONException{

		HCUtils.Log("Result ",""+responce.toString());
		JSONObject obj = new JSONObject(responce.toString());
		JSONObject internal_Obj;
		
		if (obj.has(KEY_STATUS)) {
			internal_Obj = obj.getJSONObject(KEY_STATUS);
			if (internal_Obj.has(KEY_STATUS_MSG)) {
				mStatus.setStatusMsg(internal_Obj.getString(KEY_STATUS_MSG));
			}
			if (internal_Obj.has(KEY_STATUS_ERROR)) {
				mStatus.setError(internal_Obj.getInt(KEY_STATUS_ERROR));
			}
		}

		if (obj.has(KEY_COUNT)) {
			mCount = obj.getInt(KEY_COUNT);
		}
	}
	 
	public String getStatusMessage() {
		return mStatus.getStatusMsg();
	}
 
	public int getStatusErrorCode() {
		return mStatus.getError();
	}
 
	public void setStatusMsg(int status, String errorMsg) {
		mStatus.setError(status);
		mStatus.setStatusMsg(errorMsg);
	}
}

