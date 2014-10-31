package com.mavericks.checkin;

import java.util.ArrayList;

import com.mavericks.checkin.adapters.HCUserAdapter;
import com.mavericks.checkin.client.HCClient;
import com.mavericks.checkin.client.HCIRequestListener;
import com.mavericks.checkin.client.HCServerUtils;
import com.mavericks.checkin.holders.HCAmountHolder;
import com.mavericks.checkin.holders.HCNameValuePair;
import com.mavericks.checkin.parser.HCStatusParser;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCRetryDialog;
import com.mavericks.checkin.utils.HCRetryDialog.OnRetryClickListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HCPayActivity extends HCBaseActivity implements OnClickListener,OnCheckedChangeListener {
	RadioGroup transaction;
	RadioButton pay;
	RadioButton cash;
	RelativeLayout payment;
	HCUserAdapter mAdapter;
	TextView mTxtproceed;
	TextView mTxtcproceed;
	RelativeLayout casharrival;
	HCAmountHolder mAmtHolder;
	TextView mConsultcharge,mTotalamount, mInternet;
	TextView mDiscount,mRegcharge,mConvencharge;
	boolean mbIsNewVisit = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		setContentView(R.layout.activity_hcpay);
		
		Bundle bundle = getIntent().getExtras();
		if (bundle.containsKey(HCConstants.EXTRA_TIME)) {
			mAmtHolder = bundle.getParcelable(HCConstants.EXTRA_TIME);
		}
		
		if(bundle.containsKey(HCConstants.EXTRA_IS_NW_VISIT)) {
			mbIsNewVisit = bundle.getBoolean(HCConstants.EXTRA_IS_NW_VISIT);
		}
		transaction = (RadioGroup) findViewById(R.id.rad_payment);
		pay = (RadioButton) findViewById(R.id.rad_pay);
		cash = (RadioButton) findViewById(R.id.rad_cash);
		mTxtcproceed = (TextView) findViewById(R.id.text_proceeded);
		mTxtproceed = (TextView) findViewById(R.id.text_proceed);
		payment = (RelativeLayout) findViewById(R.id.rel_pay);
		mTxtcproceed.setOnClickListener(this);
		mTxtproceed.setOnClickListener(this);
		casharrival = (RelativeLayout) findViewById(R.id.rel_cash);
		pay.setOnClickListener(this);
		cash.setOnClickListener(this);
		casharrival.setVisibility(View.GONE);
		transaction.setOnCheckedChangeListener(this);
		
		mRegcharge = (TextView) findViewById(R.id.text_regicharge);		
		mConsultcharge = (TextView) findViewById(R.id.text_consltcharge);		
		mConvencharge = (TextView) findViewById(R.id.text_convncharge);		
		mDiscount = (TextView) findViewById(R.id.text_discountcharge);		
		mInternet = (TextView) findViewById(R.id.text_intercharge);		
		mTotalamount = (TextView) findViewById(R.id.text_amountcharge);		
		
		updateUI();

	}
	
	/**
	 * updates the UI
	 */
	private void updateUI() {
		if(mbIsNewVisit) {
			mRegcharge.setText(mAmtHolder.getNewvisit_general_amount());
			mConsultcharge.setText(mAmtHolder.getNewvisit_consulation_charges());
			mConvencharge.setText(mAmtHolder.getNewvisit_internet_charges());
			mDiscount.setText(mAmtHolder.getNewvisit_discount());
			mInternet.setText(mAmtHolder.getNewvisit_internet_charges());
			mTotalamount.setText(mAmtHolder.getNewvisit_total_amount());
			
		}
		else {
			mRegcharge.setText(mAmtHolder.getRevisit_general_amount());
			mConsultcharge.setText(mAmtHolder.getRevisit_consulation_charges());
			mConvencharge.setText(mAmtHolder.getRevisit_internet_charges());
			mDiscount.setText(mAmtHolder.getRevisit_discount());
			mInternet.setText(mAmtHolder.getRevisit_internet_charges());
			mTotalamount.setText(mAmtHolder.getRevisit_total_amount());
			
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rad_pay:
			payment.findViewById(R.id.rel_pay).setVisibility(View.VISIBLE);
			casharrival.findViewById(R.id.rel_cash).setVisibility(View.GONE);
			break;
		case R.id.rad_cash:
			casharrival.findViewById(R.id.rel_cash).setVisibility(View.VISIBLE);
			payment.findViewById(R.id.rel_pay).setVisibility(View.GONE);
			break;
		case R.id.text_proceed:
			makePayment();
			break;
		case R.id.text_proceeded:
			makePayment();
			break;		
		default:
			break;
		}

	}

	
	private void makePayment() {
//		Bundle extra = getIntent().getBundleExtra(HCConstants.EXTRA_PASS);
		ArrayList<HCNameValuePair> formData = getIntent()
				.getParcelableArrayListExtra(HCConstants.EXTRA_PASS);
		if(transaction.getCheckedRadioButtonId() == R.id.rad_cash)
			formData.add(new HCNameValuePair(HCConstants.PAR_CASH_ON_ARRIVAL, "1"));
		else
			formData.add(new HCNameValuePair(HCConstants.PAR_CASH_ON_ARRIVAL, "0"));
		HCClient.getInstance().request(this, HCServerUtils.REQ_HOSPITAL_VISIT,
				null, formData, null, new HCStatusParser(), new HCIRequestListener() {

					@Override
					public void onComplete(int req_type, int status) {
						hideProgressDialog();
						if (status == HCConstants.ERROR_CODE_SUCCESS) {
							Intent intent = new Intent(HCPayActivity.this,
									HCConfirmationActivity.class);
							startActivity(intent);
						} else {
							// If we have data in database
							HCRetryDialog dialog = new HCRetryDialog(
									HCPayActivity.this,
									HCServerUtils.REQ_HOSPITAL_VISIT);
							dialog.setOnRetryClickListener(new OnRetryClickListener() {

								@Override
								public void onRetryClick(int requestType) {
									makePayment();
								}
							});
							dialog.show(HCPayActivity.this
									.getSupportFragmentManager(),
									HCHomeActivity.class.getSimpleName());
						}
					}
				});
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if(checkedId == R.id.rad_cash) {
			findViewById(R.id.rel_cash).setVisibility(View.VISIBLE);
			findViewById(R.id.rel_pay).setVisibility(View.GONE);
		}
		else {
			findViewById(R.id.rel_cash).setVisibility(View.GONE);
			findViewById(R.id.rel_pay).setVisibility(View.VISIBLE);
		}
	}
	

}
