package com.mavericks.checkin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mavericks.checkin.utils.HCAlertManager;

public class HCRegistrationActivity extends HCBaseActivity implements
		OnClickListener {

	private ProgressDialog pDialog;

	private static String url = "http://chipsy.in/Hospital_Check/all_hospital_details.php ";

	final private static int DIALOG_LOGIN = 1;
	final private static int DIALOG_SIGNUP = 2;
	final private static int DIALOG_HISTORY = 3;
	EditText emailid, pass, semailid, spass, scpass;

	String email_id, password, semail_id, spassword, eml, fl;

	// REVISIT STRING VARIABLES

	public static String mUserid = "";
	public static String specilisation = "";
	public static String selected_date = "";
	public static String t_id = "";
	public static String srname;
	public static String lname = null;
	public static String srmobile;
	public static String srhnumber;
	public static String h_id = "";
	public static String priority_appoi;
	public static String sdoname;
	public static String sremail;

	// newvisit string variables

	public static String sname;
	public static String sfname;
	public static String sage;
	public static String smobile;
	public static String shemail;
	public static String saddress;
	public static String slocation;

	public static String mname = null;
	public static String marry = null;
	public static String selectedgender = "";
	public static String llname = null;

	// String appointment = null;

	public static String amt_pay;
	public static String c_charge;
	public static String cc_charge;
	public static String disc;
	public static String ih_charge;
	public static String total;

	public static String uamt_pay;
	public static String uc_charge;
	public static String ucc_charge;
	public static String udisc;
	public static String uih_charge;
	public static String utotal;

	public static String amt_pay2;
	public static String c_charge2;
	public static String cc_charge2;
	public static String disc2;
	public static String ih_charge2;
	public static String total2;

	public static String uamt_pay2;
	public static String uc_charge2;
	public static String ucc_charge2;
	public static String udisc2;
	public static String uih_charge2;
	public static String utotal2;

	AlertDialog alertDialog3;
	AlertDialog dialogDetails = null;

	int code;
	JSONObject json;
	String message;

	private Spinner hos_spinner, spec_spinner, date_spinner, time_spinner;

	RadioGroup app_radio;
	RadioGroup visre_radio;
	RadioGroup gender_radio;

	RadioButton yes_radio, no_radio, visit_radio, revisit_radio, male_radio,
			female_radio;

	public static EditText doname, name, fname, age, mobile, hemail, address,
			location, hnumber;

	public static EditText rname, rhnumber, rmobile, remail;

	TextView signuptext;
	TextView mcheckin, mrcheckin;

	ImageView idoct, itime;
	TextView imginfo;
	ImageView imgmenu;
	LinearLayout revisitLayout;
	LinearLayout newvisitLayout;
	LinearLayout appLayout, appLayout2;
	String result, result1, hospital_id, d_special, result2, result3;
	ArrayList<String> listgroup = new ArrayList<String>();
	ArrayList<String> MnpList = new ArrayList<String>();
	ArrayList<String> MlrList = new ArrayList<String>();
	ArrayList<String> MysrList = new ArrayList<String>();
	ArrayList<String> listSpecialisation = new ArrayList<String>();
	ArrayList<String> datelist = new ArrayList<String>();
	ArrayList<String> datelists = new ArrayList<String>();

	ArrayList<String> listtime = new ArrayList<String>();
	String date;

	String[] date1;
	int day;
	int month;
	int yr;

	InputStream is;
	BufferedReader in = null;
	String data = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hcregistration);

		hos_spinner = (Spinner) findViewById(R.id.spinner_hospital);
		spec_spinner = (Spinner) findViewById(R.id.spinner_special);
		date_spinner = (Spinner) findViewById(R.id.spinner_date);
		time_spinner = (Spinner) findViewById(R.id.spinner_time);

		app_radio = (RadioGroup) findViewById(R.id.radio_app);
		visre_radio = (RadioGroup) findViewById(R.id.radio_visre);
		gender_radio = (RadioGroup) findViewById(R.id.radio_gender);

		yes_radio = (RadioButton) findViewById(R.id.radio_yes);
		no_radio = (RadioButton) findViewById(R.id.radio_no);
		visit_radio = (RadioButton) findViewById(R.id.radio_visit);
		revisit_radio = (RadioButton) findViewById(R.id.radio_revisit);
		male_radio = (RadioButton) findViewById(R.id.radio_male);
		female_radio = (RadioButton) findViewById(R.id.radio_female);

		doname = (EditText) findViewById(R.id.edt_doctor);

		// NEWVISIT

		name = (EditText) findViewById(R.id.edt_pname);
		fname = (EditText) findViewById(R.id.edt_fname);
		age = (EditText) findViewById(R.id.edt_age);
		hnumber = (EditText) findViewById(R.id.edt_hnumber);
		mobile = (EditText) findViewById(R.id.edt_mobile);
		hemail = (EditText) findViewById(R.id.edt_email);
		address = (EditText) findViewById(R.id.edt_address);
		location = (EditText) findViewById(R.id.edt_location);

		// REVISIT

		rname = (EditText) findViewById(R.id.edt_rname);
		rhnumber = (EditText) findViewById(R.id.edt_rhnumber);
		rmobile = (EditText) findViewById(R.id.edt_rmobile);
		remail = (EditText) findViewById(R.id.edt_remail);

		srname = rname.getText().toString();
		srhnumber = rhnumber.getText().toString();
		srname = rmobile.getText().toString();
		sremail = remail.getText().toString();
		sdoname = doname.getText().toString();

		revisitLayout = (LinearLayout) findViewById(R.id.revisit_form);
		newvisitLayout = (LinearLayout) findViewById(R.id.newvisit_form);
		appLayout = (LinearLayout) findViewById(R.id.app_form);

		idoct = (ImageView) findViewById(R.id.tab_doctor);
		imginfo = (TextView) findViewById(R.id.img_info);
		imgmenu = (ImageView) findViewById(R.id.img_menu);

		mcheckin = (TextView) findViewById(R.id.text_checkin);
		mrcheckin = (TextView) findViewById(R.id.text_rcheckin);

		idoct.setVisibility(View.GONE);
		appLayout.setVisibility(View.GONE);

		newvisitLayout.setVisibility(View.VISIBLE);
		revisitLayout.setVisibility(View.GONE);

		fl = HCSignInActivity.flag;

		selectedgender = "Male";
		priority_appoi = "0";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy");
		date = sdf.format(c.getTime());
		date1 = date.split(":");
		day = (Integer.parseInt(date1[0])) + 1;
		month = Integer.parseInt(date1[1]);
		yr = Integer.parseInt(date1[2]);
		Log.e("date", "d: " + date);
		Log.e("day", "d: " + day);
		populate_spinner();

		imgmenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (fl == "") {
					Toast.makeText(getApplicationContext(),
							"Please LOGIN to see your history",
							Toast.LENGTH_LONG).show();
				} else {
					showDialog(DIALOG_HISTORY);
				}
			}
		});

		imginfo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(HCRegistrationActivity.this,
						HCSignInActivity.class));
			}
		});

		app_radio.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {

				switch (checkedId) {
				case R.id.radio_no:
					priority_appoi = "0";
					appLayout.setVisibility(View.GONE);
					idoct.setVisibility(View.GONE);
					break;

				case R.id.radio_yes:
					priority_appoi = "1";

					appLayout.setVisibility(View.VISIBLE);
					idoct.setVisibility(View.VISIBLE);
					break;

				}
			}
		});

		visre_radio
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {
						case R.id.radio_visit:

							newvisitLayout.setVisibility(View.VISIBLE);
							revisitLayout.setVisibility(View.GONE);
							break;

						case R.id.radio_revisit:

							revisitLayout.setVisibility(View.VISIBLE);
							newvisitLayout.setVisibility(View.GONE);
							break;

						default:
							break;

						}

					}
				});

		gender_radio
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {

						switch (checkedId) {

						case R.id.radio_male:
							selectedgender = "Male";
							break;

						case R.id.radio_female:
							selectedgender = "Female";
							break;

						default:
							break;

						}

					}

				});

		mcheckin.setOnClickListener(this);

		mrcheckin.setOnClickListener(this);

	}

	private void populate_spinner() {
		new GetResult().execute();

	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {

		case DIALOG_HISTORY:
			LayoutInflater inflater3 = LayoutInflater.from(this);
			View dialogview3 = inflater3.inflate(R.layout.activity_hchistory,
					null);

			AlertDialog.Builder dialogbuilder3 = new AlertDialog.Builder(this);

			dialogbuilder3.setView(dialogview3);
			dialogDetails = dialogbuilder3.create();
	//		dialogDetails.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
			break;

		}

		return dialogDetails;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {

		switch (id) {

		case DIALOG_HISTORY:
			alertDialog3 = (AlertDialog) dialog;

			break;

		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.text_checkin:

			fl = HCSignInActivity.flag;
			if (isFormNewValid()) {

				if (fl == "") {

					Toast.makeText(getApplicationContext(),
							"You should LOGIN first !!!", Toast.LENGTH_SHORT)
							.show();
					startActivity(new Intent(HCRegistrationActivity.this,
							HCSignInActivity.class));

				} else {

					startActivity(new Intent(HCRegistrationActivity.this,
							HCPaymentsActivity.class));

				}

			}

			break;

		case R.id.text_rcheckin:
			fl = HCSignInActivity.flag;

			if (isFormRevisitValid()) {

				if (fl == "") {

					Toast.makeText(getApplicationContext(),
							"You should LOGIN first !!!", Toast.LENGTH_SHORT)
							.show();
					startActivity(new Intent(HCRegistrationActivity.this,
							HCSignInActivity.class));

				} else {

					startActivity(new Intent(HCRegistrationActivity.this,
							HCPaymentActivity.class));

				}

			}
			break;

		default:
			break;
		}
	}

	private boolean isFormNewValid() {

		int msg = 0;
		boolean bValid = true;

		if (name.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (fname.getText().toString().trim().length() == 0) {
			msg = R.string.err_fname;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (age.getText().toString().trim().length() == 0) {
			msg = R.string.err_age;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (hnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (mobile.getText().toString().trim().length() == 0) {
			msg = R.string.err_mobile;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (hemail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (address.getText().toString().trim().length() == 0) {
			msg = R.string.err_address;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (location.getText().toString().trim().length() == 0) {
			msg = R.string.err_location;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		} else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				hemail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			hemail.setText("");
			bValid = false;
		} else if (mobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			mobile.setText("");
			bValid = false;
		}
		if (msg != 0)
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		return bValid;

	}

	private boolean isFormRevisitValid() {

		int msg = 0;
		boolean bValid = true;

		if (rname.getText().toString().trim().length() == 0) {
			msg = R.string.err_name;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();

			bValid = false;
		}

		else if (rhnumber.getText().toString().trim().length() == 0) {
			msg = R.string.err_hnumber;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();

			bValid = false;
		} else if (rmobile.getText().toString().trim().length() == 0) {
			msg = R.string.err_mobile;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();

			bValid = false;
		} else if (remail.getText().toString().trim().length() == 0) {
			msg = R.string.err_email;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			bValid = false;
		}

		else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
				remail.getText().toString()).matches()) {
			msg = R.string.err_invalidemail;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			remail.setText("");
			bValid = false;
		} else if (rmobile.getText().toString().trim().length() < 10) {
			msg = R.string.err_invalidphone;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			rmobile.setText("");
			bValid = false;
		} else if (rmobile.getText().toString().trim().length() > 10) {
			msg = R.string.err_invalidphone;
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG)
					.show();
			rmobile.setText("");
			bValid = false;
		}

		if (msg != 0) {
			HCAlertManager.showAlertWithOneBtn(this, getString(msg), null);
		}

		return bValid;

	}

	private class GetResult extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

		}

		@Override
		protected Void doInBackground(Void... arg0) throws NullPointerException {

			ServiceHandler sh = new ServiceHandler();

			result = sh.makeServiceCall(url, ServiceHandler.GET);

			Log.d("Response: ", "> " + result);

			try {
				JSONObject jo = new JSONObject(result);

				JSONObject j = jo.getJSONObject("data");

				JSONArray ja = j.getJSONArray("hospital_master");

				for (int i = 0; i < ja.length(); i++) {
					JSONObject j1 = ja.getJSONObject(i);
					String group = j1.getString("hospital_name");

					Log.e("pass3", "h:  " + group);

					listgroup.add(j1.optString("hospital_name"));

				}

			} catch (Exception e) {
				Log.e("Fail 3", e.toString());
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			hos_spinner.setAdapter(new ArrayAdapter<String>(
					HCRegistrationActivity.this, R.layout.my_spinner_text,
					listgroup));

			hos_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
					String place = hos_spinner.getSelectedItem().toString();
					Log.e("item", "" + place);
					long idd = hos_spinner.getSelectedItemId();
					Log.e("id", "i" + idd);

					if (place.equals("KMC")) {

						h_id = "10001-140001";
						new GetSpecialisation(h_id).execute();
					}
					if (place.equals("City hospital")) {

						h_id = "10002-140002";
						new GetSpecialisation(h_id).execute();
					}
					if (place.equals("Mithra Hospital")) {

						h_id = "10003-140003";
						new GetSpecialisation(h_id).execute();
					}
					if (place.equals("Gandhi hospital")) {

						h_id = "10004-140004";
						new GetSpecialisation(h_id).execute();
					}

				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					// TODO Auto-generated method stub

				}

			});

		}
	}

	private class GetSpecialisation extends AsyncTask<Void, Void, Void> {
		String h_id, hosp_id;
		ArrayList<String> mnpspecial = new ArrayList<String>();
		ArrayList<String> mlrspecial = new ArrayList<String>();
		ArrayList<String> mysrspecial = new ArrayList<String>();

		public GetSpecialisation(String h_id2) {
			this.h_id = h_id2;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

		}

		@Override
		protected Void doInBackground(Void... arg0) throws NullPointerException {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("hospital_id", h_id));

			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://chipsy.in/Hospital_Check/get_all_specilization_update.php");
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				Log.e("pass 2.1", "connection success ");

			} catch (Exception e) {
				Log.e("Fail 2.1", e.toString());
				Toast.makeText(getApplicationContext(), "Invalid IP Address",
						Toast.LENGTH_LONG).show();
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");

				}

				is.close();

				result3 = sb.toString();

				Log.e("result2", "h" + result3);
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());

				Toast.makeText(getApplicationContext(), " Input reading fail",
						Toast.LENGTH_SHORT).show();

			}

			try {

				JSONObject jo = new JSONObject(result3);

				JSONObject j = jo.getJSONObject("data");
				JSONObject j1 = jo.getJSONObject("session");
				hosp_id = j1.getString("hospital_id");
				Log.e("hosp_id", "id: " + hosp_id);

				JSONArray ja = j.getJSONArray("specilization_details");
				{
					for (int i = 0; i < ja.length(); i++) {
						JSONObject j2 = ja.getJSONObject(i);
						String special_id = j2.getString("specialization ");
						String special_name = j2
								.getString("specialization_name");
						Log.e("group", "h:  " + special_id);

						mnpspecial.add(special_name);

					}

				}

				JSONArray ja1 = j.getJSONArray("date_details");
				{

					for (int k = 0; k < ja1.length(); k++) {
						JSONObject j3 = ja1.getJSONObject(k);
						String limit = j3.getString("date_count");
						Log.e("limit", "l" + limit);
						int limit1 = Integer.parseInt(limit);
						for (int i = 0; i < limit1; i++) {

							Log.e("day", "l" + day);

							if (day <= 31 && month == 1 || day <= 28
									&& month == 2 || day <= 31 && month == 3
									|| day <= 30 && month == 4 || day <= 31
									&& month == 5 || day <= 30 && month == 6
									|| day <= 31 && month == 7 || day <= 31
									&& month == 8 || day <= 30 && month == 9
									|| day <= 31 && month == 10 || day <= 30
									&& month == 11 || day <= 30 && month == 12) {

								String day1 = Integer.toString(day);
								String month1 = Integer.toString(month);
								String yr1 = Integer.toString(yr);
								String date = "" + day1 + "-" + month1 + "-"
										+ yr1;
								datelist.add(date);
								day++;
							}

							else {
								day = 1;
								month = month + 1;
								String day1 = Integer.toString(day);
								String month1 = Integer.toString(month);
								String yr1 = Integer.toString(yr);
								String date = "" + day1 + "-" + month1 + "-"
										+ yr1;
								datelist.add(date);
								day++;

							}

						}
					}
				}

			} catch (Exception e) {

				Log.e("Fail 3", e.toString());
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			ArrayAdapter adapter = new ArrayAdapter<String>(
					HCRegistrationActivity.this, R.layout.my_spinner_text,
					mnpspecial);

			adapter.notifyDataSetChanged();

			spec_spinner.setAdapter(adapter);
			// adapter.setNotifyOnChange(true);

			spec_spinner
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {

							String specil = spec_spinner.getSelectedItem()
									.toString();

							if (specil.equals("Cardiology")) {

								// specilisation = "101";
								ArrayAdapter adapter = new ArrayAdapter<String>(
										HCRegistrationActivity.this,
										R.layout.my_spinner_text, datelist);
								adapter.notifyDataSetChanged();
								date_spinner.setAdapter(adapter);
								specilisation = "101";

							}

							else if (specil.equals("Nephrology")) {

								// specilisation = "101";
								ArrayAdapter adapter = new ArrayAdapter<String>(
										HCRegistrationActivity.this,
										R.layout.my_spinner_text, datelist);
								adapter.notifyDataSetChanged();
								date_spinner.setAdapter(adapter);
								specilisation = "102";
							}

							else if (specil.equals("Psychiatry")) {

								// specilisation = "101";
								ArrayAdapter adapter = new ArrayAdapter<String>(
										HCRegistrationActivity.this,
										R.layout.my_spinner_text, datelist);
								adapter.notifyDataSetChanged();
								date_spinner.setAdapter(adapter);
								specilisation = "103";
							}

							else {

								// datelist.clear();

								specilisation = "";
								ArrayAdapter adapter = new ArrayAdapter<String>(
										HCRegistrationActivity.this,
										R.layout.my_spinner_text, datelist);
								adapter.notifyDataSetChanged();
								date_spinner.setAdapter(adapter);

							}

							// datelist.clear();

							date_spinner
									.setOnItemSelectedListener(new OnItemSelectedListener() {

										@Override
										public void onItemSelected(
												AdapterView<?> parent,
												View view, int position, long id) {
											selected_date = date_spinner
													.getSelectedItem()
													.toString();
											Log.e("date", "d:  "
													+ selected_date);

											new GetTime(specilisation, hosp_id,
													selected_date).execute();

										}

										@Override
										public void onNothingSelected(
												AdapterView<?> parent) {
											// TODO Auto-generated method stub

										}
									});

						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub

						}
					});
		}

	}

	private class GetTime extends AsyncTask<Void, Void, Void> {
		String h_id, special, date;

		public GetTime(String specilisation, String hosp_id2,
				String selected_date) {
			this.h_id = hosp_id2;
			this.special = specilisation;
			this.date = selected_date;
			Log.e("se", "s" + h_id + "" + special + "" + date);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog

		}

		@Override
		protected Void doInBackground(Void... arg0) throws NullPointerException {
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("hospital_id", h_id));
			nameValuePairs.add(new BasicNameValuePair("specialization_id",
					special));
			nameValuePairs.add(new BasicNameValuePair("visiting_date", date));

			try {

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						"http://chipsy.in/Hospital_Check/get_all_timing_update.php");

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				HttpResponse response = httpclient.execute(httppost);

				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				Log.e("pass 2.1", "connection success ");

			} catch (Exception e) {
				Log.e("Fail 2.1", e.toString());
				Toast.makeText(getApplicationContext(), "Invalid IP Address",
						Toast.LENGTH_LONG).show();
			}
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");

				}
				is.close();

				result3 = sb.toString();

				Log.e("result2", "h" + result3);
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());

				Toast.makeText(getApplicationContext(), " Input reading fail",
						Toast.LENGTH_SHORT).show();

			}
			try {

				JSONObject jo = new JSONObject(result3);

				JSONObject j = jo.getJSONObject("data");

				JSONArray ja = j.getJSONArray("timing_details");
				for (int i = 0; i < ja.length(); i++) {
					JSONObject j2 = ja.getJSONObject(i);

					t_id = j2.getString("timing_id");
					String time = j2.getString("appointment_timings");
					String avail = j2.getString("availability");
					Log.e("time", "h:  " + time);
					Log.e("availability", "h:  " + avail);

					String timenavail = time + " (Available " + avail + ")";

					listtime.add(timenavail);
				}

				JSONArray ja1 = j.getJSONArray("amount_details");

				for (int k = 0; k < ja1.length(); k++) {

					JSONObject k1 = ja1.getJSONObject(k);

					JSONArray ja2 = k1.getJSONArray("new_visit");
					for (int x = 0; x < ja2.length(); x++) {
						JSONObject k2 = ja2.getJSONObject(x);

						JSONArray ja3 = k2.getJSONArray("normal_amount");
						for (int i = 0; i < ja3.length(); i++) {
							JSONObject jj = ja3.getJSONObject(i);

							amt_pay = jj.getString("amount_pay");
							c_charge = jj.getString("consultation_charges");
							cc_charge = jj
									.getString("checkin_convenience_fees");
							disc = jj.getString("discount");
							ih_charge = jj
									.getString("internet_handling_charges");
							total = jj.getString("total_amount");
							Log.e("total",
									"total amount of new visit normal:  "
											+ total);

						}

						JSONArray ja4 = k2.getJSONArray("urgent_amount");
						for (int i = 0; i < ja4.length(); i++) {
							JSONObject jj1 = ja4.getJSONObject(i);

							uamt_pay = jj1.getString("amount_pay");
							uc_charge = jj1.getString("consultation_charges");
							ucc_charge = jj1
									.getString("checkin_convenience_fees");
							uih_charge = jj1
									.getString("internet_handling_charges");
							udisc = jj1.getString("discount");

							utotal = jj1.getString("total_amount");
							Log.e("total amt",
									"total amount of new visit urgent:  "
											+ utotal);

						}

					}

					JSONArray ja5 = k1.getJSONArray("re_visit");
					for (int x = 0; x < ja5.length(); x++) {
						JSONObject k2 = ja5.getJSONObject(x);

						JSONArray ja3 = k2.getJSONArray("normal_amount");
						for (int i = 0; i < ja3.length(); i++) {
							JSONObject jj = ja3.getJSONObject(i);

							amt_pay2 = jj.getString("amount_pay");
							c_charge2 = jj.getString("consultation_charges");
							cc_charge2 = jj
									.getString("checkin_convenience_fees");
							ih_charge2 = jj
									.getString("internet_handling_charges");

							disc2 = jj.getString("discount");

							total2 = jj.getString("total_amount");
							Log.e("total",
									"total amount of new visit normal:  "
											+ total2);

						}

						JSONArray ja4 = k2.getJSONArray("urgent_amount");
						for (int i = 0; i < ja4.length(); i++) {
							JSONObject jj1 = ja4.getJSONObject(i);

							uamt_pay2 = jj1.getString("amount_pay");
							uc_charge2 = jj1.getString("consultation_charges");
							ucc_charge2 = jj1
									.getString("checkin_convenience_fees");
							uih_charge2 = jj1
									.getString("internet_handling_charges");
							udisc2 = jj1.getString("discount");

							utotal2 = jj1.getString("total_amount");
							Log.e("total amt",
									"total amount of new visit urgent:  "
											+ utotal2);

						}

					}

				}

			} catch (Exception e) {
				Log.e("Fail 3", e.toString());
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			ArrayAdapter adapter = new ArrayAdapter<String>(
					HCRegistrationActivity.this, R.layout.my_spinner_text,
					listtime);
			// adapter.setNotifyOnChange(true);
			adapter.notifyDataSetChanged();
			time_spinner.setAdapter(adapter);
		}
	}
}
