/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABErrorDialog.java
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


import com.mavericks.checkin.R;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.mavericks.checkin.utils.HCAlertManager.IAlertButtonsListner;
import com.mavericks.checkin.utils.HCAlertManager.IAlertDismissListner;
import com.mavericks.checkin.utils.HCAlertManager.IRetryListner;

@SuppressLint("ValidFragment")
public class HCErrorDialog extends DialogFragment implements OnClickListener {

	public static int ONE_BTN_VIEW = 1;
	public static int TWO_BTN_VIEW = 2;
	public static int PLAIN_VIEW = 3;
	public static String SETTINGS = "Settings";
	public static String UPDATE = "Update";

	private Context mContext;

	private Dialog mDialog;
	private TextView mTxtTitle;
	// private TextView mTxtMsg;
	private Button mBtnOk;
	private Button mBtnCancel;

	private String mTitle = "";
	// private String mMsg = "";
	private IAlertButtonsListner mAlertListnr;
	private IAlertDismissListner mAlertDissmiss;
	private IRetryListner mRetryListnr;
	private int mViewSelected = ONE_BTN_VIEW; // default selection
	private String mPosBtnLbl;
//	Switch switchA;
	public HCErrorDialog(){}
	public HCErrorDialog(Context context) {
		mContext = context;
	}

	public void setTitleForDialog(String title) {
		mTitle = title;
	}

	// public void setMessageForDialog(String message){
	// mMsg = message;
	// }
	//
	public String getMsg() {
		return mTitle;
	}

	public void setViewForDialog(int selection) {
		mViewSelected = selection;
	}

	public void setIAlertListener(IAlertButtonsListner lis) {
		mAlertListnr = lis;
	}

	public void setmAlertDissmiss(IAlertDismissListner mAlertDissmiss) {
		this.mAlertDissmiss = mAlertDissmiss;
	}

	public void setIRetryListener(IRetryListner lis) {
		mRetryListnr = lis;
	}

	public void setPosBtnLabel(String posBtnLbl) {
		mPosBtnLbl = posBtnLbl;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		if(mContext == null)
			return null;
		mDialog = new Dialog(mContext, R.style.Theme_Dialog_No_Title);

		mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// To dismiss the dialog on touching outside dialog.
		mDialog.setCanceledOnTouchOutside(true);

		if (mViewSelected == ONE_BTN_VIEW) {
			mDialog.setContentView(R.layout.view_cust_alert_one_btn_dlg);
		} else if (mViewSelected == TWO_BTN_VIEW) {
			mDialog.setContentView(R.layout.view_cust_alert_two_btn_dlg);
			mBtnCancel = (Button) mDialog.findViewById(R.id.btn_negative);
			mBtnCancel.setOnClickListener(this);
		}
		

		if(mViewSelected != PLAIN_VIEW) {
		mTxtTitle = (TextView) mDialog.findViewById(R.id.txt_title);
		// mTxtMsg = (TextView) mDialog.findViewById(R.id.txt_msg);
		mBtnOk = (Button) mDialog.findViewById(R.id.btn_positive);

		mBtnOk.setOnClickListener(this);
		mTxtTitle.setText(mTitle);
		// mTxtMsg.setText(mMsg);

		if (!TextUtils.isEmpty(mPosBtnLbl))
			mBtnOk.setText(mPosBtnLbl);

		if (TextUtils.isEmpty(mTitle))
			mTxtTitle.setVisibility(View.GONE);
		// if(TextUtils.isEmpty(mMsg))
		// mTxtMsg.setVisibility(View.GONE);
		}
		else{
//			switchA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
//			{
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
//				{
//					if (isChecked)
//						Utils.editPrefBool(Constants.PREF_SET, true, mContext);
//					else
//						Utils.editPrefBool(Constants.PREF_SET, false, mContext);
//				}
//			});

		}
			
		mDialog.show();

		return mDialog;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_positive:

			if (null != mPosBtnLbl && mPosBtnLbl.equalsIgnoreCase(SETTINGS)) {
				mContext.startActivity(new Intent(
						android.provider.Settings.ACTION_SETTINGS));
				mDialog.dismiss();
				break;
			}
			else if(null != mPosBtnLbl && mPosBtnLbl.equalsIgnoreCase(UPDATE)) {
				Uri marketUri = Uri.parse("market://details?id=" + mContext.getPackageName());
				Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
				mContext.startActivity(marketIntent);
				mDialog.dismiss();
				break;
			}

			if (null != mAlertListnr) {
				mAlertListnr.onPositiveClicked();
			}
			mDialog.dismiss();

			break;

		case R.id.btn_negative:

			if (null != mAlertListnr)
				mAlertListnr.onNegativeClicked();

			mDialog.dismiss();

			break;
		}
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);

		if (mAlertDissmiss != null)
			mAlertDissmiss.onDissmiss();
	}
}
