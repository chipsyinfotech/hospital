/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABServerUtils.java
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
package com.mavericks.checkin.client;

public class HCServerUtils {

   
    public static final int PUBLIC_IP = 100;
    public static final int LOCAL_IP = 101;

   
    public static final String PATH_LOGIN = "/user_register.php";
    public static final String PATH_HEALTH = "/health_and_beauty.php";
    public static final String PATH_LIFESTYLE = "/lifestyle.php";
    public static final String PATH_TEXT_MESSAGE = "image";
    public static final String PATH_ABHARAN_LOGIN = "/user_login.php";
    		
    public static final int REQ_LOGIN = 100;
    public static final int REQ_GET_HEALTH = 101;
    public static final int REQ_GET_LIFESTYLE = 102;
    public static final int REQ_ABHARAN_LOGIN = 103;
   
    
    private static String getDomailUrl(int type) {

		String url = "http://chipsy.in/Abharana";
		return url;
    }

    public static String getURL(int type) {

	String url = getDomailUrl(type);
	String path = "";
	switch (type) {


	case REQ_LOGIN:
	    path = PATH_LOGIN;
	    break;
	case REQ_GET_HEALTH:
		 path = PATH_HEALTH;
		break;
		
	case REQ_GET_LIFESTYLE:
		 path = PATH_LIFESTYLE;
		break;
		
	case REQ_ABHARAN_LOGIN:
		path = PATH_ABHARAN_LOGIN;
		break;
	}

	return url + path;
    }

    public static int getRequestType(int type) {
	int reqType = HCClient.REQ_TYPE_GET; // default

	switch (type) {
	
	case REQ_LOGIN:
	case REQ_ABHARAN_LOGIN:
	    reqType = HCClient.REQ_TYPE_POST;
	    break;

	case REQ_GET_HEALTH:		
	case REQ_GET_LIFESTYLE:
		reqType = HCClient.REQ_TYPE_GET;
		break;
	}

	return reqType;
    }

}
