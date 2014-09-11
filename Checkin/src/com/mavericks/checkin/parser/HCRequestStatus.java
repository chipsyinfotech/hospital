/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABRequestStatus.java
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


/**
 * @author vijayalaxmi
 *
 */
public class HCRequestStatus {
	String mStatusMsg;
	int mError = -1;
	
	public void setStatusMsg(String mStatusMsg) {
		this.mStatusMsg = mStatusMsg;
	}
	
	public void setError(int mError) {
		this.mError = mError;
	}
	
	public String getStatusMsg() {
		return mStatusMsg;
	}
	
	public int getError() {
		return mError;
	}

	
}
