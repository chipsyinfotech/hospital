package com.mavericks.checkin.parser;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mavericks.checkin.holders.HCBaseHolder;
import com.mavericks.checkin.holders.HCHospitalHolder;

public class HCGetTimeParser extends HCBaseJsonParser {

	public static final String SESSION = "session";
	public static final String HOSPITAL_ID = "hospital_id";
	public static final String DATA = "data";
	public static final String TIMING_DETAIL = "timing_details";
	public static final String TIMING_ID = "timing_id";
	public static final String APPOINTMENT_TIME = "appointment_timings";
	public static final String AVAILABILITY = "availability";
	public static final String AMOUNT_DETAIL = "amount_details";
	public static final String NEWVISIT = "new_visit";
	public static final String NORMAL_AMOUNT = "normal_amount";
	public static final String AMOUNT_PAY = "amount_pay";
	public static final String CONSULT_CHARGE = "consultation_charges";
	public static final String CONVIENCE_FEES = "checkin_convenience_fees";
	public static final String DISCOUNT = "discount";
	public static final String INTERNET_CHARGE = "internet_handling_charges";
	public static final String TOTAL_AMOUNT = "total_amount";
	public static final String URGENT_AMOUNT = "urgent_amount";
	public static final String REVISIT = "re_visit";
	HCHospitalHolder holder;

	public HCGetTimeParser() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#initialize(java.lang.
	 * StringBuilder)
	 */
	@Override
	public void initialize(StringBuilder document) throws IOException,
			JSONException {
		super.initialize(document);

		JSONObject obj = new JSONObject(document.toString());
		String hospital_id = null;
		if (obj.has(SESSION)) {

			JSONObject jsonSession = obj.getJSONObject(SESSION);
			if (jsonSession.has(HOSPITAL_ID))
				hospital_id = jsonSession.getString(HOSPITAL_ID);
		}
		if (obj.has(DATA)) {
			JSONObject data = obj.getJSONObject(DATA);

			if (data.has(TIMING_DETAIL)) {
				JSONArray detail = data.getJSONArray(TIMING_DETAIL);

				for (int i = 0; i < detail.length(); i++) {

					JSONObject mcontent = detail.getJSONObject(i);

					HCHospitalHolder holder = new HCHospitalHolder();

					if (!mcontent.isNull(TIMING_ID) && mcontent.has(TIMING_ID)) {
						holder.setTime_id(mcontent.getString(TIMING_ID));
					}

					if (!mcontent.isNull(APPOINTMENT_TIME)
							&& mcontent.has(APPOINTMENT_TIME)) {
						holder.setAppointment_time(mcontent
								.getString(APPOINTMENT_TIME));
					}

				}
			}
			if (data.has(AMOUNT_DETAIL)) {
				JSONArray amountdetail = data.getJSONArray(AMOUNT_DETAIL);

				for (int i = 0; i < amountdetail.length(); i++) {

					JSONObject amount = amountdetail.getJSONObject(i);
					if (amount.has(NEWVISIT)) {
						JSONArray newvisit = amount.getJSONArray(NEWVISIT);

						for (int j = 0; j < newvisit.length(); j++) {
							JSONObject visit = newvisit.getJSONObject(j);

							if (visit.has(NORMAL_AMOUNT)) {
								JSONArray normalamount = visit
										.getJSONArray(NORMAL_AMOUNT);
								for (int k = 0; k < normalamount.length(); k++) {

									JSONObject namount = normalamount
											.getJSONObject(k);
									HCHospitalHolder holder = new HCHospitalHolder();
									if (!namount.isNull(AMOUNT_PAY)
											&& namount.has(AMOUNT_PAY)) {
										holder.setAmount_pay(namount
												.getString(AMOUNT_PAY));
									}
									if (!namount.isNull(CONSULT_CHARGE)
											&& namount.has(CONSULT_CHARGE)) {
										holder.setConsult_charge(namount
												.getString(CONSULT_CHARGE));
									}
									if (!namount.isNull(CONVIENCE_FEES)
											&& namount.has(CONVIENCE_FEES)) {
										holder.setConvience_charge(namount
												.getString(CONVIENCE_FEES));
									}
									if (!namount.isNull(DISCOUNT)
											&& namount.has(DISCOUNT)) {
										holder.setDiscount(namount
												.getString(DISCOUNT));
									}
									if (!namount.isNull(INTERNET_CHARGE)
											&& namount.has(INTERNET_CHARGE)) {
										holder.setInternet_charge(namount
												.getString(INTERNET_CHARGE));
									}
									if (!namount.isNull(TOTAL_AMOUNT)
											&& namount.has(TOTAL_AMOUNT)) {
										holder.setTotal_amount(namount
												.getString(TOTAL_AMOUNT));
									}
								}

							}
							if (visit.has(URGENT_AMOUNT)) {
								JSONArray urgentamount = visit
										.getJSONArray(URGENT_AMOUNT);
								for (int k = 0; i < urgentamount.length(); k++) {

									JSONObject uamount = urgentamount
											.getJSONObject(k);
									HCHospitalHolder holder = new HCHospitalHolder();
									if (!uamount.isNull(AMOUNT_PAY)
											&& uamount.has(AMOUNT_PAY)) {
										holder.setAmount_pay(uamount
												.getString(AMOUNT_PAY));
									}
									if (!uamount.isNull(CONSULT_CHARGE)
											&& uamount.has(CONSULT_CHARGE)) {
										holder.setConsult_charge(uamount
												.getString(CONSULT_CHARGE));
									}
									if (!uamount.isNull(CONVIENCE_FEES)
											&& uamount.has(CONVIENCE_FEES)) {
										holder.setConvience_charge(uamount
												.getString(CONVIENCE_FEES));
									}
									if (!uamount.isNull(DISCOUNT)
											&& uamount.has(DISCOUNT)) {
										holder.setDiscount(uamount
												.getString(DISCOUNT));
									}
									if (!uamount.isNull(INTERNET_CHARGE)
											&& uamount.has(INTERNET_CHARGE)) {
										holder.setInternet_charge(uamount
												.getString(INTERNET_CHARGE));
									}
									if (!uamount.isNull(TOTAL_AMOUNT)
											&& uamount.has(TOTAL_AMOUNT)) {
										holder.setTotal_amount(uamount
												.getString(TOTAL_AMOUNT));
									}
								}
							}
						}
					}
					if (amount.has(REVISIT)) {
						JSONArray revisit = amount.getJSONArray(REVISIT);

						for (int j = 0; j < revisit.length(); j++) {
							JSONObject rvisit = revisit.getJSONObject(j);

							if (rvisit.has(NORMAL_AMOUNT)) {
								JSONArray normalamount = rvisit
										.getJSONArray(NORMAL_AMOUNT);
								for (int k = 0; k < normalamount.length(); k++) {

									JSONObject namount = normalamount
											.getJSONObject(k);
									HCHospitalHolder holder = new HCHospitalHolder();
									if (!namount.isNull(AMOUNT_PAY)
											&& namount.has(AMOUNT_PAY)) {
										holder.setAmount_pay(namount
												.getString(AMOUNT_PAY));
									}
									if (!namount.isNull(CONSULT_CHARGE)
											&& namount.has(CONSULT_CHARGE)) {
										holder.setConsult_charge(namount
												.getString(CONSULT_CHARGE));
									}
									if (!namount.isNull(CONVIENCE_FEES)
											&& namount.has(CONVIENCE_FEES)) {
										holder.setConvience_charge(namount
												.getString(CONVIENCE_FEES));
									}
									if (!namount.isNull(DISCOUNT)
											&& namount.has(DISCOUNT)) {
										holder.setDiscount(namount
												.getString(DISCOUNT));
									}
									if (!namount.isNull(INTERNET_CHARGE)
											&& namount.has(INTERNET_CHARGE)) {
										holder.setInternet_charge(namount
												.getString(INTERNET_CHARGE));
									}
									if (!namount.isNull(TOTAL_AMOUNT)
											&& namount.has(TOTAL_AMOUNT)) {
										holder.setTotal_amount(namount
												.getString(TOTAL_AMOUNT));
									}
								}

							}
							if (rvisit.has(URGENT_AMOUNT)) {
								JSONArray urgentamount = rvisit
										.getJSONArray(URGENT_AMOUNT);
								for (int k = 0; i < urgentamount.length(); k++) {

									JSONObject uamount = urgentamount
											.getJSONObject(k);
									HCHospitalHolder holder = new HCHospitalHolder();
									if (!uamount.isNull(AMOUNT_PAY)
											&& uamount.has(AMOUNT_PAY)) {
										holder.setAmount_pay(uamount
												.getString(AMOUNT_PAY));
									}
									if (!uamount.isNull(CONSULT_CHARGE)
											&& uamount.has(CONSULT_CHARGE)) {
										holder.setConsult_charge(uamount
												.getString(CONSULT_CHARGE));
									}
									if (!uamount.isNull(CONVIENCE_FEES)
											&& uamount.has(CONVIENCE_FEES)) {
										holder.setConvience_charge(uamount
												.getString(CONVIENCE_FEES));
									}
									if (!uamount.isNull(DISCOUNT)
											&& uamount.has(DISCOUNT)) {
										holder.setDiscount(uamount
												.getString(DISCOUNT));
									}
									if (!uamount.isNull(INTERNET_CHARGE)
											&& uamount.has(INTERNET_CHARGE)) {
										holder.setInternet_charge(uamount
												.getString(INTERNET_CHARGE));
									}
									if (!uamount.isNull(TOTAL_AMOUNT)
											&& uamount.has(TOTAL_AMOUNT)) {
										holder.setTotal_amount(uamount
												.getString(TOTAL_AMOUNT));
									}
								}
							}
						}
					}
				}
			}
		}
	}/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getModelsCount()
	 */

	@Override
	public int getModelsCount() {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolderAt(int)
	 */
	@Override
	public HCBaseHolder getDataHolderAt(int position) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataHolder()
	 */
	@Override
	public HCBaseHolder getDataHolder() {
		return holder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.viacom18.spotlight.models.STAJsonDataModel#getDataList()
	 */
	@Override
	public List<? extends HCBaseHolder> getDataList() {
		return null;
	}

}
