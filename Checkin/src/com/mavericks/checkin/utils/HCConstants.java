/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABConstants.java
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

package com.mavericks.checkin.utils;

/**
 * @author vijayalaxmi
 * 
 */
public class HCConstants {

	public static final int SCR_HOME = 100;
	// Preference Name
	public static final String PREF_NAME = "pref_hospital";
	public static final String PREF_USRNAME = "pref_usr_nm";
	public static final String PREF_PIC = "pref_pic";
	public static final String PREF_PASSWORD = "pref_pswrd";
	public static final String PREF_USRID = "pref_usr_id";
	public static final String PREF_PHONE = "pref_phone";
	public static final String PREF_FBID = "pref_fb_id";
	public static final String PREF_TWID = "pref_tw_id";
	public static final String PREF_TRID = "pref_tr_id";
	public static final String PREF_HID = "pref_hospital_id";
	public static final String PREF_EMAIL = "pref_email_address";
	
	public static final String PREF_ADDRESS = "pref_hospital_address";
	public static final String PREF_HGROUP = "pref_hospital_group";
	public static final String PREF_DIV_REG = "pref_isDevice_registered";

	public static final String EXTRA_IS_REGISTERED = "extra_isRegistered";
	public static final String EXTRA_CONTENT = "extra_content";
	public static final String EXTRA_CONTENT_LIST = "extra_content_list";
	public static final String EXTRA_POSITION = "extra_position";

	public static final String MULTIPART_FILE_KEY = "file";

	public static final int IMG_PROFILE = 100;

	public static final int REQUEST_CODE = 100;

	// params
	public static final String PAR_LOGIN_TYPE = "login_type";
	public static final String PAR_USR_NM = "user_name";
	public static final String PAR_EMAIL_ID = "email_address";
	public static final String PAR_PSWRD = "password";
	public static final String PAR_MOB_NUMB = "mobile_number";
	public static final String PAR_APP_KEY = "app_key";
	public static final String PAR_SPECIAL_ID = "special_id";
	public static final String PAR_VISIT_DATE= "visiting_date";
	public static final String PAR_TIME_ID = "timing_id";
	public static final String PAR_FNAME = "first_name";
	public static final String PAR_MOTHER = "mother_name";
	public static final String PAR_MARRIED = "married";
	public static final String PAR_LNAME = "last_name";
	public static final String PAR_HOSPITAL_ID= "hospital_id";
	public static final String PAR_PASS= "priority_pass";
	public static final String PAR_TOTAL_AMOUNT= "total_amount";
	public static final String PAR_FANAME= "father_name";
	public static final String PAR_GENDER= "gender";
	public static final String PAR_ADDRESS= "address";
	public static final String PAR_AGE= "age";
	public static final String PAR_APPOINTMENT= "prior_appointment";
	public static final String PAR_DNAME= "doctor_name";
	public static final String PAR_HNUMBER= "user_hospital_no";

	public static final String PAR_PROFILE_PIC = "profile_path";
	public static final String PARAM_USERID = "user_id";

	/* Error Codes for Api */
	// Server Error Codes
	public static final int ERROR_CODE_SUCCESS = 0;
	public static final int ERROR_CODE_GENERIC = 1;
	public static final int ERROR_CODE_EMAIL_REG = 2;
	public static final int ERROR_CODE_EMAIL_INVALID = 3;
	// Application Related Error Codes use after 600
	public static final int ERROR_CODE_PASS_MISSMATCH = 600;
	public static final int ERROR_CODE_PASS_SHORT = 601;
	public static final int ERROR_CODE_FIELD_MISSING = 602;
	public static final int ERROR_CODE_NO_NETWORK = 603;
	public static final int ERROR_CODE_IO_EXCEPTION = 604;
	public static final int ERROR_CODE_JSON_EXCEPTION = 605;
	public static final int ERROR_CODE_EXCEPTION = 606;
	public static final int ERROR_CODE_URL_ERROR = 607;
	public static final int ERROR_CODE_VERIFY_EMAIL = 608;
	public static final int ERROR_CODE_ERROR_SHARE = 609;
	public static final int ERROR_CODE_PAS_CHANGED_SUCC = 611;
	public static final int ERROR_CODE_DEL_ACC = 612;
	public static final int ERROR_CODE_SERVER_NOT_ACCESSIBLE = 613;
	public static final int ERROR_CODE_NOT_SIGNED_IN = 614;
	public static final int ERROR_CODE_NO_DATA_FOUND = 615;
	public static final int ERROR_CODE_REFRESH = 616;
	
}
