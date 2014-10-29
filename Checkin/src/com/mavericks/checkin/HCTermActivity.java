package com.mavericks.checkin;

import android.content.Intent;
import android.os.Bundle;

public class HCTermActivity extends HCBaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcterm);
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(HCTermActivity.this, HCHomeActivity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(0, android.R.anim.fade_out);
	}
}
