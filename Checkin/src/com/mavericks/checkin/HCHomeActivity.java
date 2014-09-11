package com.mavericks.checkin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.mavericks.checkin.client.HCImageDownloader;
import com.mavericks.checkin.client.HCSession;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

/*public class HCHomeActivity extends HCBaseActionBarActivity implements OnClickListener{

	ImageView mImgAbt,mImgLifestyle,mImgHealth,mImgFav,mImgProfile;
	TextView mTxtNm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abhome);
		setUpActionBar(ACTION_HOME);		
		setUpSlidingMenu();
		initUi();
	}

	private void initUi() {
		mTxtNm = (TextView) findViewById(R.id.txt_name);
		mTxtNm.setText(ABSession.getInstance().getUsrNm(this));
		
		mImgAbt = (ImageView) findViewById(R.id.img_about);
		mImgFav = (ImageView) findViewById(R.id.img_fav);
		mImgHealth = (ImageView) findViewById(R.id.img_health);
		mImgLifestyle = (ImageView) findViewById(R.id.img_lifestyle);
		mImgProfile = (ImageView) findViewById(R.id.img_profile);
		mImgAbt.setOnClickListener(this);
		mImgFav.setOnClickListener(this);
		mImgHealth.setOnClickListener(this);
		mImgLifestyle.setOnClickListener(this);

		HCUtils.Log(" ======== Img Url"+HCSession.getInstance().getPic(this));
		HCImageDownloader.setCircularImg(this,mImgProfile, ABSession.getInstance().getPic(this),
				HCConstants.IMG_PROFILE, R.drawable.profile_picture, false);

	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch(view.getId()) {
		case R.id.img_about:
			
			break;
			
		case R.id.img_fav:
			
			break;
			
		case R.id.img_health:
			
			break;
			
		case R.id.img_lifestyle:
			intent = new Intent();
			intent.setClass(HCHomeActivity.this, ABHealthAndBeautyActivity.class);
			break;
		}
		
		if(intent != null)
			startActivity(intent);
	}
}*/
