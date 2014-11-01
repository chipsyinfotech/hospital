package com.mavericks.checkin;


import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.mavericks.checkin.fragments.HCMainMenuFrag;
import com.mavericks.checkin.views.HCLoadingDialog;



public class HCBaseActionBarActivity extends SlidingFragmentActivity implements
		OnCancelListener {
   
	private boolean isPaused = false;
	public static final int ACTION_HOME = 100;
	public static final int ACTION_ABOUT = 101;
	public static final int ACTION_FAV = 102;
	public static final int ACTION_LIFESTLYE = 103;
	public static final int ACTION_HEALTH = 104;
	public static final int ACTION_CONTACT = 105;
	public static final int ACTION_MAP = 106;

	protected ActionBar mAction_bar;
	private HCLoadingDialog mProgressDialog;
	private String msg;
	protected View mViewAction;
	protected HCMainMenuFrag mSlidingMenuFrag;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setBehindContentView(R.layout.frame_menu);
		if (savedInstanceState != null) {
			msg = savedInstanceState.getString("msg");
			if (msg != null)
				showProgressDialog(msg, false);
		}
		
	}

	public OnClickListener headerMenuClicked = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			
		}
	};
	
	protected void setUpSlidingMenu() {

		if (mSlidingMenuFrag == null) {
			FragmentTransaction t = this.getSupportFragmentManager()
					.beginTransaction();
			mSlidingMenuFrag = new HCMainMenuFrag();
			
			t.replace(R.id.frame, mSlidingMenuFrag);
			t.commit();
		}

		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
//		sm.setShadowDrawable(R.drawable.sliding_shadow);
		sm.setBehindWidth(getResources().getDimensionPixelOffset(
				R.dimen.slidingmenu_width));
		sm.setBackgroundColor(Color.parseColor("#00000000"));
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);		
		sm.setBehindScrollScale(1);
		
		
	}
	
	
	
	protected void setUpActionBar(int type) {
		mAction_bar = getSupportActionBar();
		LayoutInflater inflator = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		mViewAction = inflator.inflate(R.layout.view_actionbar, null);
		mAction_bar.setCustomView(mViewAction);
		mAction_bar.setDisplayShowCustomEnabled(true);
		mAction_bar.setHomeButtonEnabled(false);
		mAction_bar.setDisplayShowHomeEnabled(false);
		mAction_bar.setDisplayShowTitleEnabled(false);
		mAction_bar.setDisplayHomeAsUpEnabled(false);
		mAction_bar.setBackgroundDrawable(null);
		
//		mViewAction.findViewById(R.id.actbar_btn_home).setOnClickListener(headerMenuClicked);
//		ImageView srch = (ImageView) mViewAction.findViewById(R.id.actbar_btn_srch);
//		srch.setOnClickListener(headerMenuClicked);
//		TextView txtTitle = (TextView)mViewAction.findViewById(R.id.action_title);
	
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
	

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
