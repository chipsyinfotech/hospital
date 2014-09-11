/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABUtils.java
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * @author vijayalaxmi
 * 
 */
public class HCUtils {
	public static boolean LOG_DEBUG = true;
	public static final int DB_VERSION = 22;
	public static final boolean FINAL_BUILD = false;
	public static final String BUILD_VERSION = "v0.23";

	/**
	 * prints the Log based on LOG_DEBUG
	 * 
	 * @param msg
	 */
	public static void Log(String msg) {
		if (LOG_DEBUG) {
			Log.d("Abharan", msg);
		}
	}

	public static void Log(String tag, String msg) {
		if (LOG_DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static String getHMSFormat(int milliseconds) {

		int seconds = (int) (milliseconds / 1000) % 60;
		int minutes = (int) ((milliseconds / (1000 * 60)) % 60);
		int hours = (int) ((milliseconds / (1000 * 60 * 60)) % 24);

		return String.format("%02d:%02d:%02d", hours, minutes, seconds);

	}

	/* Removes all the html tags and also new line character from the string. */
	public static String stripHtml(String html) {
		if (html == null) {
			return null;
		}
		String stringToReturn = Html.fromHtml(html).toString();
		stringToReturn = stringToReturn.replace("&nbsp;", " ");
		stringToReturn = Html.fromHtml(stringToReturn).toString();
		stringToReturn = stringToReturn.replace("\\", "");
		return stringToReturn;
	}

	public static String getStringViews(long count) {
		return count != 1 ? " Views" : " View";
	}

	public static String getStringRetweets(long count) {
		return count != 1 ? " Retweets" : " Retweet";
	}

	public static String getStringFavourites(long count) {
		return count != 1 ? " Favorites" : " Favorite";
	}

	public static String getStringLikes(long count) {
		return count != 1 ? " Likes" : " Like";
	}

	public static String getStringComments(long count) {
		return count != 1 ? " Comments" : " Comment";
	}

	public static String getStringFollowers(int count) {
		if (count != 1) {
			return (getStringFormattedNumber(count) + " followers");
		} else {
			return (getStringFormattedNumber(count) + " follower");
		}
	}

	/*
	 * Returns the count in the format ##,##,###
	 */
	public static String getStringFormattedNumber(int count) {
		NumberFormat formatter = null;
		if (count > 999999) {
			count /= 1000000;
			formatter = new DecimalFormat(",##,###M+");
		} else if (count > 999) {
			count /= 1000;
			formatter = new DecimalFormat(",##,###K+");
		} else {
			formatter = new DecimalFormat(",##,###");
		}

		return "" + formatter.format(count);
	}

	/**
	 * return Md5 of a String
	 * 
	 * @param in
	 * @return
	 */
	public static String getMd5(String in) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.reset();
			digest.update(in.getBytes());
			byte[] a = digest.digest();
			int len = a.length;
			StringBuilder sb = new StringBuilder(len << 1);
			for (int i = 0; i < len; i++) {
				sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
				sb.append(Character.forDigit(a[i] & 0x0f, 16));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * checks if internet is On
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean isInternetOn(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = conn.getActiveNetworkInfo();
		if (networkInfo != null) {
			return networkInfo.isConnectedOrConnecting();
		}
		return false;
	}

	/**
	 * checks whether the url is image url or not.
	 * 
	 * @param url
	 * @return boolean
	 */
	public static boolean isUrlImage(String url) {
		if (url.contains("png") || url.contains("jpeg") || url.contains("jpg"))
			return true;
		return false;
	}

	/**
	 * verifies the email is valid
	 * 
	 * @param email
	 * @return
	 */

	public static boolean isEmailValid(String email) {
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	/**
	 * shows the Toast
	 * 
	 * @param context
	 * @param stringid
	 */
	public static void showToast(Context context, int stringid) {
		Toast.makeText(context, context.getString(stringid), Toast.LENGTH_LONG)
				.show();
	}

	/**
	 * shows the Toast
	 * 
	 * @param context
	 * @param stringid
	 */
	public static void showToast(Context context, String strMessage) {
		if (!TextUtils.isEmpty(strMessage))
			Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
	}

	/**
	 * returns the String preference value
	 */
	public static String getPrefString(String name, String value,
			Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				HCConstants.PREF_NAME, Context.MODE_PRIVATE);
		return prefs.getString(name, value);
	}

	public static long getPrefLong(String name, Long value, Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				HCConstants.PREF_NAME, Context.MODE_PRIVATE);
		return prefs.getLong(name, value);
	}

	/**
	 * edits the String preference value
	 */

	public static void editPrefString(String name, String value, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		prefs.edit().putString(name, value).commit();
	}

	public static void editPrefLong(String name, Long value, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		prefs.edit().putLong(name, value).commit();
	}

	/**
	 * returns the Int preference value
	 */

	public static int getPrefInt(String name, int defValue, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		return prefs.getInt(name, defValue);
	}

	/**
	 * edits the Int preference value
	 */

	public static void editPrefInt(String name, int value, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		prefs.edit().putInt(name, value).commit();
	}

	/**
	 * returns the Boolean preference value
	 */
	public static boolean getPrefBool(String name, boolean defValue, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		return prefs.getBoolean(name, defValue);
	}

	/**
	 * edits the Boolean preference value
	 */

	public static void editPrefBool(String name, boolean value, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		prefs.edit().putBoolean(name, value).commit();
	}

	/**
	 * checks if the preference exists
	 */

	public static boolean isPrefExist(String name, Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		return prefs.contains(name);
	}

	public static void removePreference(String name,Context c) {
		SharedPreferences prefs = c.getSharedPreferences(HCConstants.PREF_NAME,
				Context.MODE_PRIVATE);
		prefs.edit().remove(name);
	}

	/**
	 * To check whether particular application is installed or not.
	 * 
	 * @param context
	 * @param uri
	 *            - Package name of the application.
	 * @return
	 */
	public static boolean appInstalledOrNot(Context context, String uri) {
		PackageManager pm = context.getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
			Log("App installed.");
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

	// To identify whether device is tablet or phone.
	public static boolean isTablet(Context context) {
		boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
		boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
		return (xlarge || large);
	}

	public static void showStatusBar(Window window) {
		WindowManager.LayoutParams attrs = window.getAttributes();
		attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(attrs);
	}
}

