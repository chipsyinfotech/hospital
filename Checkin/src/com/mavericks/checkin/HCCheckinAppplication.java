/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCCheckinAppplication.java
 * @Project:
 *		Checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 09-Sep-2014
 */

package com.mavericks.checkin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Base64;

import com.mavericks.checkin.client.HCImageDownloader;
//import com.mavericks.abharan.db.ABDbModel;
//import com.mavericks.abharan.db.ABIDbConfiguration;
//import com.mavericks.abharan.holders.ABContentHolder;
//import com.mavericks.abharan.pushnotification.Utilities;
import com.mavericks.checkin.utils.HCUtils;

/**  sssssssss
 * @author sandeep maddy
 * vcvc
 */
public class HCCheckinAppplication extends Application {

	private static Context context;
	public static String PACKAGENAME;
//hi
	public HCCheckinAppplication() {
		context = this;
	}

	public static Context getContext() {
		return context;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		PACKAGENAME = getPackageName();
		//ABDbHelper.getInstance(getApplicationContext(),
				//DbConfiguration.getInstance(getApplicationContext()));
		//ABImageDownloader.initAquery();
		//Utilities.registerDevice(getApplicationContext());
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.mavericks.abharan", PackageManager.GET_SIGNATURES);
			for (android.content.pm.Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				HCUtils.Log(Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			HCUtils.Log("Name not found");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			HCUtils.Log("NoSuchAlgorithmException");
		}

	}

/*	public static class DbConfiguration implements ABIDbConfiguration {

		private String databaseName;
		private String databasePath;
		private List<ABDbModel> models;
		private int version;

		public static DbConfiguration getInstance(Context context) {

			List<ABDbModel> list = new ArrayList<ABDbModel>();
			list.add(new ABContentHolder());

			DbConfiguration config = new DbConfiguration();
			config.setDatabaseName("abharan.db");

			if (context != null && context.getExternalFilesDir(null) != null)
				config.setDatabasePath(context.getExternalFilesDir(null)
						.getAbsolutePath());
			else
				config.setDatabasePath(null);

			config.setDatabaseVersion(ABUtils.DB_VERSION);
			config.setModels(list);

			return config;
		}

		@Override
		public String getDatabaseName() {
			return databaseName;
		}

		@Override
		public String getDatabasePath() {
			return databasePath;
		}

		@Override
		public List<ABDbModel> getModels() {
			return models;
		}

		@Override
		public int getDatabaseVersion() {
			return version;
		}

		@Override
		public void setDatabaseName(String databaseName) {
			this.databaseName = databaseName;

		}

		@Override
		public void setDatabasePath(String databasePath) {
			this.databasePath = databasePath;

		}

		@Override
		public void setModels(List<ABDbModel> models) {
			this.models = models;

		}

		@Override
		public void setDatabaseVersion(int version) {
			this.version = version;

		}

	}
*/
	@Override
	public void onLowMemory() {
		HCUtils.Log(" LOW MEMORY");
		// clear all memory cached images when system is in low memory
		// note that you can configure the max image cache count, see
		// CONFIGURATION
		HCImageDownloader.clearAllImages();
	}
}
