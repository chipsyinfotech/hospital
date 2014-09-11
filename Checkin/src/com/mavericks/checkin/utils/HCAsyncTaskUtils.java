
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABAsyncTaskUtils.java
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

import android.os.AsyncTask;

public class HCAsyncTaskUtils {

    public static void execute(AsyncTask task,Object obj[]){
	if(HCAndroidVersionUtil.getAndroidVersion()<14){
	    
	    task.execute(obj);
	
	}else{
	
	    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, obj);
	
	}
    }
}
