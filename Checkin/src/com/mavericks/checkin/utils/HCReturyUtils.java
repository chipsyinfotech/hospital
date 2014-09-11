
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABReturyUtils.java
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

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.mavericks.checkin.R;

 
public class HCReturyUtils {

    public static void showRetryFrame(View view,OnClickListener onClick,final int req_type) {
 
	//View retry = (View) findViewById(R.id.retry_frame);
	
	ImageView mBtnRetry = (ImageView) view.findViewById(R.id.retry_btn);
	mBtnRetry.setOnClickListener(onClick);
 	view.setVisibility(View.VISIBLE);
	mBtnRetry.setTag(req_type); 
 	 

     }
    
    public static void hideRetryFrame(View view){
	
	view.setVisibility(View.GONE);
	
    }
}
