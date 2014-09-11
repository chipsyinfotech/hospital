/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCBaseActivity.java
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
//
//
package com.mavericks.checkin;

//sssssssssssssssssssssssss


import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;

import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.views.HCLoadingDialog;

public class HCBaseActivity extends Activity implements

		OnCancelListener {

	private boolean isPaused = false;
	
	private HCLoadingDialog mProgressDialog;
	private String msg;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		HCClient.getInstance().cancelPreviousTasks();
//		setBehindContentView(R.layout.frame_menu);
		if (savedInstanceState != null) {
			msg = savedInstanceState.getString("msg");
			if (msg != null)
				showProgressDialog(msg, false);
		}
		
	}

	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		if (mProgressDialog != null && mProgressDialog.isShowing())
			outState.putString("msg", msg);
	}

	/**
	 * Shows a progress dialog with the message passed.
	 * 
	 * @param msg
	 *            Message to be displayed.
	 */
	public void showProgressDialog(String msg, boolean mbCancel) {
		if (!isFinishing()) {
			if (mProgressDialog != null) {
				mProgressDialog.dismiss();

				mProgressDialog = null;
			}

			mProgressDialog = new HCLoadingDialog(this,

					R.style.Theme_Dialog_No_Title);
			if (mbCancel)
				mProgressDialog.setOnCancelListener(this);
			else
				mProgressDialog.setCancelable(false);
			mProgressDialog.show();

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		hideProgressDialog();

	}

	/**
	 * Hides the current showing dialog.
	 * 
	 */
	public void hideProgressDialog() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				if (mProgressDialog != null && mProgressDialog.isShowing()) {
					mProgressDialog.dismiss();
				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		isPaused = false;
	}

	@Override
	protected void onPause() {
		super.onPause();
		isPaused = true;
	}

	public boolean isPaused() {
		return isPaused;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.content.DialogInterface.OnCancelListener#onCancel(android.content
	 * .DialogInterface)
	 */
	@Override
	public void onCancel(DialogInterface dialog) {
		finish();
	}
	
}
