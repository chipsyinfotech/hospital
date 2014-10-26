package com.mavericks.checkin;

import com.mavericks.checkin.adapters.HCUserAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HCPayActivity extends HCBaseActivity implements OnClickListener {
RadioGroup transaction;
RadioButton pay;
RadioButton cash;
RelativeLayout payment;
HCUserAdapter mAdapter;
TextView mTxtproceed;
TextView mTxtcproceed;
RelativeLayout casharrival;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcpay);
		transaction=(RadioGroup)findViewById(R.id.rad_payment);
		pay=(RadioButton)findViewById(R.id.rad_pay);
		cash=(RadioButton)findViewById(R.id.rad_cash);
		mTxtcproceed=(TextView)findViewById(R.id.text_proceeded);
		mTxtproceed=(TextView)findViewById(R.id.text_proceed);
		payment=(RelativeLayout)findViewById(R.id.rel_pay);
		mTxtcproceed.setOnClickListener(this);
		mTxtproceed.setOnClickListener(this);
		casharrival=(RelativeLayout)findViewById(R.id.rel_cash);
		pay.setOnClickListener(this);
		cash.setOnClickListener(this);
		casharrival.setVisibility(View.GONE);
	}
	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.rad_pay:payment.findViewById(R.id.rel_pay).setVisibility(View.VISIBLE);
	casharrival.findViewById(R.id.rel_cash).setVisibility(View.GONE);
		break;
	case R.id.rad_cash:casharrival.findViewById(R.id.rel_cash).setVisibility(View.VISIBLE);
	payment.findViewById(R.id.rel_pay).setVisibility(View.GONE);
	break;
	case R.id.text_proceed:
		Intent i=new Intent(HCPayActivity.this,HCConfirmationActivity.class);
		startActivity(i);
		finish();
		break;
	case R.id.text_proceeded:
		Intent intent=new Intent(HCPayActivity.this,HCConfirmationActivity.class);
		startActivity(intent);
		finish();
		break;
	default:
		break;
	}
		
	}

}
