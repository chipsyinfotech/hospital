package com.mavericks.checkin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class HCSplashActivity extends Activity {
	 private final int SPLASH_DISPLAY_LENGTH = 4000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcsplash);
		        new Handler().postDelayed(new Runnable(){
		            @Override
		            public void run() {
		                /* Create an Intent that will start the Menu-Activity. */
		                Intent mainIntent = new Intent(HCSplashActivity.this,HCHomeActivity.class);
		                startActivity(mainIntent);
		           
		                finish();
		            }
		        }, SPLASH_DISPLAY_LENGTH);
		    }
		
	}

	

