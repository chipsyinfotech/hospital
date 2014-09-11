
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABAndroidVersionUtil.java
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

import android.os.Build;

public class HCAndroidVersionUtil {

    public static int getAndroidVersion(){
	return Build.VERSION.SDK_INT;
    }
    
    public static boolean isBeforeHoneyComb(){
	return (Build.VERSION.SDK_INT < 11)?true:false;
    }
}
