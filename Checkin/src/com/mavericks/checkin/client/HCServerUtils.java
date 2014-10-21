/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCServerUtils.java
 * @Project:
 *		 checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */
package com.mavericks.checkin.client;

public class HCServerUtils {

	public static final int PUBLIC_IP = 100;
	public static final int LOCAL_IP = 101;

	public static final String PATH_TEXT_MESSAGE = "image";
	public static final String PATH_GET_HOSPITAL = "/all_hospital_details.php";
	public static final String PATH_GET_SPECIALIZATION = "/get_all_specilization.php";
	public static final String PATH_HOSPITAL_LOGIN = "/user_login.php";
	public static final String PATH_HOSPITAL_HISTORY = "/user_histry.php";
	public static final String PATH_HOSPITAL_REG = "/user_register.php";
	public static final String PATH_FORGOT_PSWRD = "/forgot_password.php";
	public static final String PATH_HOSPITAL_VISIT = "/newvisit_submit_record.php";
	public static final String PATH_HOSPITAL_REVISIT = "/revisit_submit_record.php";
	public static final String PATH_HOSPITAL_TIME = "/get_all_timing.php";
	public static final String PATH_HOSPITAL_DATE = "/get_all_date.php";

	public static final int REQ_LOGIN = 100;

	public static final int REQ_HOSPITAL_LOGIN = 101;
	public static final int REQ_HOSPITAL_REG = 102;
	public static final int REQ_FORGOT_PASS = 103;
	public static final int REQ_HOSPITAL_VISIT = 104;
	public static final int REQ_HOSPITAL_REVISIT = 105;
	public static final int REQ_GET_HOSPITAL = 106;
	public static final int REQ_GET_HISTORY = 107;
	public static final int REQ_GET_SPECIALIZATION = 108;
	public static final int REQ_GET_TIME = 109;
	public static final int REQ_GET_DATE = 110;
	
	
	private static String getDomailUrl(int type) {

		String url = "http://chipsy.in/hospital/apkfiles";
		return url;
	}

	public static String getURL(int type) {

		String url = getDomailUrl(type);
		String path = "";
		switch (type) {

		case REQ_HOSPITAL_REG:
			path = PATH_HOSPITAL_REG;
			break;
		case REQ_GET_SPECIALIZATION:
			path = PATH_GET_SPECIALIZATION;
			break;
			

		case REQ_HOSPITAL_LOGIN:
			path = PATH_HOSPITAL_LOGIN;
			break;
		case REQ_FORGOT_PASS:
			path = PATH_FORGOT_PSWRD;
			break;
		case REQ_HOSPITAL_VISIT:
			path = PATH_HOSPITAL_VISIT;
			break;
		case REQ_HOSPITAL_REVISIT:
			path = PATH_HOSPITAL_REVISIT;
			break;
		case REQ_GET_HOSPITAL:
			path = PATH_GET_HOSPITAL;
			break;
		case REQ_GET_TIME:
			path = PATH_HOSPITAL_TIME;
			break;
		case REQ_GET_DATE:
			path = PATH_HOSPITAL_DATE;
			break;
		case REQ_GET_HISTORY:
			path = PATH_HOSPITAL_HISTORY;
			break;

		}

		return url + path;
	}

	public static int getRequestType(int type) {
		int reqType = HCClient.REQ_TYPE_GET;

		switch (type) {

		case REQ_LOGIN:
		case REQ_GET_HOSPITAL:
			reqType = HCClient.REQ_TYPE_GET;
		case REQ_GET_HISTORY:
			reqType = HCClient.REQ_TYPE_GET;
			break;
		case REQ_GET_SPECIALIZATION:
			reqType = HCClient.REQ_TYPE_GET;
			break;
		case REQ_HOSPITAL_LOGIN:
			reqType = HCClient.REQ_TYPE_POST;
			break;
		case REQ_HOSPITAL_REG:

			reqType = HCClient.REQ_TYPE_POST;
			break;
		case REQ_FORGOT_PASS:
			reqType = HCClient.REQ_TYPE_POST;
			break;
		case REQ_HOSPITAL_VISIT:
			reqType = HCClient.REQ_TYPE_POST;
			break;
		case REQ_GET_TIME:
			reqType = HCClient.REQ_TYPE_GET;
			break;
		case REQ_GET_DATE:
			reqType = HCClient.REQ_TYPE_GET;
			break;
			
			
		case REQ_HOSPITAL_REVISIT:
			reqType = HCClient.REQ_TYPE_POST;
			break;
	
		}

		return reqType;
	}

}
