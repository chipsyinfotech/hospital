package com.mavericks.checkin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class HCAboutUsActivity extends Activity implements OnClickListener {
	ImageView email;
	ImageView call;
	ImageView fb;
	ImageView twitter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcabout_us);
		email = (ImageView) findViewById(R.id.img_mail);
		call = (ImageView) findViewById(R.id.img_call);
		fb = (ImageView) findViewById(R.id.img_fb);
		twitter = (ImageView) findViewById(R.id.img_twitt);
		fb.setOnClickListener(this);
		twitter.setOnClickListener(this);
		email.setOnClickListener(this);
		call.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_mail:
			sendEmail();
			break;
		case R.id.img_fb:
			facebook();
			break;
		case R.id.img_call:
			call();
			break;
		case R.id.img_twitt:
			twitter();
			break;

		}

	}

	protected void sendEmail() {
		Log.i("Send email", "");

		String[] TO = { "sff@gmail.com" };
		String[] CC = { "mcmohd@gmail.com" };
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");

		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		emailIntent.putExtra(Intent.EXTRA_CC, CC);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			finish();
			Log.i("Finished sending email...", "");
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(HCAboutUsActivity.this,
					"There is no email client installed.", Toast.LENGTH_SHORT)
					.show();
		}
	}

	protected void call() {
		Intent callIntent = new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:198"));
		startActivity(callIntent);
	}

	private void facebook() {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("fb://profile/hospital"));
			startActivity(intent);
		} catch (Exception e) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.facebook.com")));
		}
	}

	private void twitter() {
		try {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("tw://profile"));
			startActivity(intent);
		} catch (Exception e) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.twitter.com")));
		}
	}

	@Override
	public void onBackPressed() {

		super.onBackPressed();
		Intent intent = new Intent(HCAboutUsActivity.this, HCHomeActivity.class);
		startActivity(intent);

		overridePendingTransition(0, android.R.anim.fade_out);
	}
}
