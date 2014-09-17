/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABFontManager.java
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
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mavericks.checkin.R;

public class HCFontManager {
	// ** This enum is tightly coupled with the enum in res/values/attrs.xml! **
	// ** Make sure their orders stay the same **

	public static final int FNT_BTN = 1;
	public static final int FNT_DLG = 2;
	public static final int FNT_EDIT_SMALL = 3;
	public static final int FNT_EDIT_LARGE = 4;
	public static final int FNT_BUTTON = 5;
	public static final int FNT_ABOUT = 6;
	public static final int FNT_FOUNDER = 7;
	public static final int FNT_MENU = 8;
	public static final int FNT_WELCOME = 9;
	public static final int FNT_HM_NM = 10;
	public static final int FNT_TITLE = 11;
	public static final int FNT_TEXT = 12;
	public static final int FNT_AMOUNT = 13;
	public static final int FNT_TEXTS = 14;
	public static final int FNT_PRIOR = 15;
	public static final int FNT_LOGIN = 16;
	public static final int FNT_LOG = 17;


	public enum Font {

		FNT_LIGHT("fonts/OpenSans-Light.ttf"), // 1
		FNT_BOLD("fonts/OpenSans-Bold.ttf"), // 2
		FNT_REGULAR("fonts/OpenSans-Regular.ttf"), // 2
		FNT_SEMI_BOLD("fonts/OpenSans-Semibold.ttf"), // 2
		FNT_EXTRABOLD("fonts/OpenSans-ExtraBold.ttf"); // 2

		public final String fileName;

		private Font(String name) {
			fileName = name;
		}
	}

	public static void setFont(TextView tv, int fontId) {

		Font font = getFontFromId(fontId);
		if (tv != null) {
			if (getFontColor(fontId) != 0)
				tv.setTextColor(tv.getContext().getResources()
						.getColor(getFontColor(fontId)));
			float actual = tv.getContext().getResources()
					.getDimension(getFontDimens(fontId));
			float den = tv.getContext().getResources().getDisplayMetrics().density;
			float size = actual / den;

			tv.setTextSize(size);
			Typeface typeface = Typeface.createFromAsset(tv.getContext()
					.getAssets(), font.fileName);
			tv.setTypeface(typeface);
		} else
			HCUtils.Log(" Txt null");
	}

	public static void setFont(TextView tv, Font font) {
		Typeface typeface = Typeface.createFromAsset(tv.getContext()
				.getAssets(), font.fileName);
		tv.setTypeface(typeface);
	}

	public static int getFontColor(int fontId) {
		switch (fontId) {

		case FNT_BTN:
		case FNT_PRIOR:
			return R.color.light_black;
		case FNT_DLG:
		case FNT_EDIT_LARGE:
		case FNT_FOUNDER:
		case FNT_EDIT_SMALL:
		case FNT_BUTTON:
			return android.R.color.black;
		case FNT_ABOUT:
			return R.color.neon_pink;

		case FNT_MENU:
		case FNT_TITLE:
			return R.color.white;
		case FNT_LOGIN:
			return R.color.white;
		case FNT_LOG:
			return R.color.dark_red;


		case FNT_HM_NM:
			return R.color.white;
		case FNT_TEXT:
			return R.color.dark_red;
		case FNT_TEXTS:
			return android.R.color.black;
		case FNT_WELCOME:
			return R.color.welcome;

		}
		return android.R.color.black;
	}

	public static Font getFontFromId(int fontId) {
		switch (fontId) {

		case FNT_BTN:
		case FNT_TEXT:
			return Font.FNT_LIGHT;
		case FNT_DLG:
		case FNT_BUTTON:
			return Font.FNT_LIGHT;
		case FNT_PRIOR:
			return Font.FNT_LIGHT;
		case FNT_EDIT_LARGE:
		case FNT_EDIT_SMALL:
		case FNT_MENU:
			return Font.FNT_REGULAR;
		case FNT_TEXTS:
			return Font.FNT_LIGHT;
		case FNT_ABOUT:
			return Font.FNT_BOLD;

		case FNT_LOGIN:
			return Font.FNT_BOLD;
		case FNT_LOG:
			return Font.FNT_BOLD;
		case FNT_FOUNDER:
			return Font.FNT_BOLD;

		case FNT_HM_NM:
		case FNT_TITLE:
		case FNT_WELCOME:
			return Font.FNT_BOLD;

		}

		return Font.FNT_LIGHT;
	}

	private static int getFontDimens(int fontId) {

		// VIUtils.Log(" Font id >>>> "+fontId);

		switch (fontId) {

		case FNT_BTN:
			return R.dimen.sz_ss;
		case FNT_BUTTON:
			return R.dimen.sz_ms;
		case FNT_TEXT:
			return R.dimen.sz_sm;
		case FNT_PRIOR:
			return R.dimen.sz_ss;
		case FNT_TEXTS:
			return R.dimen.sz_sm;
		case FNT_MENU:
			return R.dimen.sz_sl;
		case FNT_DLG:
			return R.dimen.sz_sm;
		case FNT_LOGIN:
			return R.dimen.sz_ms;
		case FNT_LOG:
			return R.dimen.sz_ms;

		case FNT_EDIT_LARGE:
			return R.dimen.sz_mm;
		case FNT_EDIT_SMALL:
			return R.dimen.sz_sl;
		case FNT_ABOUT:
			return R.dimen.sz_mx;
		case FNT_FOUNDER:
			return R.dimen.sz_ss;
		case FNT_TITLE:
			return R.dimen.sz_sm;

		case FNT_WELCOME:
		case FNT_HM_NM:
			return R.dimen.sz_sm;

		}

		return R.dimen.sz_mm;
	}

	public static void setFontFromAttributeSet(Context context,
			AttributeSet attrs, TextView tv) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.customView);
		HCFontManager.setFont(tv,
				array.getInteger(R.styleable.customView_font_type, 0));
		array.recycle();
	}

}
