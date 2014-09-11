package com.mavericks.checkin.views;

/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		STALoadingDialog.java
 * @Project:
 *		Stardom
 * @Abstract:
 *
 * @Copyright:
 *		Copyright © 2014, Viacom 18 Media Pvt. Ltd. All Rights Reserved
 Written under contract by Robosoft Technologies Pvt. Ltd.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)

 Created by Vijayalaxmi Nayak

 */

import com.mavericks.checkin.R;
import android.app.Dialog;
import android.content.Context;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mavericks.checkin.utils.HCUtils;

public class HCLoadingDialog extends Dialog {

	ImageView mImgProgress;
	private onLoadingDlgBackPressListener mListener;

	/*
	 * Constructor LoadingDialog
	 */
	public HCLoadingDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dlg_loading);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		HCUtils.showStatusBar(getWindow());
		setCancelable(true);

		mImgProgress = (ImageView) findViewById(R.id.img_loader);
		startLoadingAnimation(context);
	}

	/*
	 * start the loading animation
	 */
	public void startLoadingAnimation(Context context) {
		Animation rotate = AnimationUtils.loadAnimation(context,
				R.anim.anim_rotate);
		rotate.setRepeatMode(Animation.INFINITE);
		mImgProgress.startAnimation(rotate);
	}

	/*
	 * close the dialog
	 */
	public void dismissLoadingDlg() {
		mImgProgress.clearAnimation();
		dismiss();
	}

	@Override
	public void onBackPressed() {

		if (null != mListener) {
			mListener.onLoadingDlgBackPress();
		}

		super.onBackPressed();
	}

	public interface onLoadingDlgBackPressListener {
		public void onLoadingDlgBackPress();
	}

	public void setBackPressListener(onLoadingDlgBackPressListener mListener) {
		this.mListener = mListener;
	}

}
