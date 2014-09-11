
package com.mavericks.checkin.utils;

public class HCBooleanUtils {

	public static int boolToInt(Boolean condition){
		return (condition != null && condition) ?1:0;
	}
	public static boolean intToBool(Integer intBool){
		return (intBool==null || intBool==0)?false:true;
	}
}
