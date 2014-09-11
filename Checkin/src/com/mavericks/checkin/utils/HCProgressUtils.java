/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABProgressUtils.java
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

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import com.mavericks.checkin.R;

/**
 * @author vijayalaxmi
 * 
 */
public class HCProgressUtils {

	public static void showProgressBar(Context context, View root, int id) {

		if (context == null)
			return;
		
		View view = (View) root.findViewById(id);

		ProgressBar prgBar = (ProgressBar) view
				.findViewById(R.id.progressBarCircular);
		Animation rotate = AnimationUtils.loadAnimation(context,
				R.anim.anim_rotate);
		prgBar.setVisibility(View.VISIBLE);
		prgBar.startAnimation(rotate);

		

	}

	public static void hideProgressBar(View root, int id) {

		if (null != root) {
			View view = (View) root.findViewById(id);
			ProgressBar prgBar = (ProgressBar) view
					.findViewById(R.id.progressBarCircular);
			prgBar.clearAnimation();
			prgBar.setVisibility(View.GONE);

		}
	}
}
