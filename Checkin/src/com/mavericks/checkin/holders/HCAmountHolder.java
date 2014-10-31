package com.mavericks.checkin.holders;

import android.os.Parcel;
import android.os.Parcelable;

public class HCAmountHolder implements Parcelable {
	String newvisit_type;
	String newvisit_general_amount;
	String newvisit_consulation_charges;
	String newvisit_internet_charges;
	String newvisit_discount;
	String newvisit_total_amount;
	String newvisit_total_pass;
	String revisit_type;
	String revisit_general_amount;
	String revisit_consulation_charges;
	String revisit_internet_charges;
	String revisit_discount;
	String revisit_total_amount;
	String revisit_total_pass;

	public HCAmountHolder() {
		// TODO Auto-generated constructor stub
	}

	public HCAmountHolder(Parcel in) {
		readFromParcel(in);
	}

	public String getNewvisit_type() {
		return newvisit_type;
	}

	public void setNewvisit_type(String newvisit_type) {
		this.newvisit_type = newvisit_type;
	}

	public String getNewvisit_general_amount() {
		return newvisit_general_amount;
	}

	public void setNewvisit_general_amount(String newvisit_general_amount) {
		this.newvisit_general_amount = newvisit_general_amount;
	}

	public String getNewvisit_consulation_charges() {
		return newvisit_consulation_charges;
	}

	public void setNewvisit_consulation_charges(
			String newvisit_consulation_charges) {
		this.newvisit_consulation_charges = newvisit_consulation_charges;
	}

	public String getNewvisit_internet_charges() {
		return newvisit_internet_charges;
	}

	public void setNewvisit_internet_charges(String newvisit_internet_charges) {
		this.newvisit_internet_charges = newvisit_internet_charges;
	}

	public String getNewvisit_discount() {
		return newvisit_discount;
	}

	public void setNewvisit_discount(String newvisit_discount) {
		this.newvisit_discount = newvisit_discount;
	}

	public String getNewvisit_total_amount() {
		return newvisit_total_amount;
	}

	public void setNewvisit_total_amount(String newvisit_total_amount) {
		this.newvisit_total_amount = newvisit_total_amount;
	}

	public String getNewvisit_total_pass() {
		return newvisit_total_pass;
	}

	public void setNewvisit_total_pass(String newvisit_total_pass) {
		this.newvisit_total_pass = newvisit_total_pass;
	}

	public String getRevisit_type() {
		return revisit_type;
	}

	public void setRevisit_type(String revisit_type) {
		this.revisit_type = revisit_type;
	}

	public String getRevisit_general_amount() {
		return revisit_general_amount;
	}

	public void setRevisit_general_amount(String revisit_general_amount) {
		this.revisit_general_amount = revisit_general_amount;
	}

	public String getRevisit_consulation_charges() {
		return revisit_consulation_charges;
	}

	public void setRevisit_consulation_charges(
			String revisit_consulation_charges) {
		this.revisit_consulation_charges = revisit_consulation_charges;
	}

	public String getRevisit_internet_charges() {
		return revisit_internet_charges;
	}

	public void setRevisit_internet_charges(String revisit_internet_charges) {
		this.revisit_internet_charges = revisit_internet_charges;
	}

	public String getRevisit_discount() {
		return revisit_discount;
	}

	public void setRevisit_discount(String revisit_discount) {
		this.revisit_discount = revisit_discount;
	}

	public String getRevisit_total_amount() {
		return revisit_total_amount;
	}

	public void setRevisit_total_amount(String revisit_total_amount) {
		this.revisit_total_amount = revisit_total_amount;
	}

	public String getRevisit_total_pass() {
		return revisit_total_pass;
	}

	public void setRevisit_total_pass(String revisit_total_pass) {
		this.revisit_total_pass = revisit_total_pass;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(newvisit_type);
		dest.writeString(newvisit_general_amount);
		dest.writeString(newvisit_consulation_charges);
		dest.writeString(newvisit_internet_charges);
		dest.writeString(newvisit_discount);
		dest.writeString(newvisit_total_amount);
		dest.writeString(newvisit_total_pass);
		dest.writeString(revisit_type);
		dest.writeString(revisit_general_amount);
		dest.writeString(revisit_consulation_charges);
		dest.writeString(revisit_internet_charges);
		dest.writeString(revisit_discount);
		dest.writeString(revisit_total_amount);
		dest.writeString(revisit_total_pass);
	}

	public void readFromParcel(Parcel in) {

		newvisit_type = in.readString();
		newvisit_general_amount = in.readString();
		newvisit_consulation_charges = in.readString();
		newvisit_internet_charges = in.readString();
		newvisit_discount = in.readString();
		newvisit_total_amount = in.readString();
		newvisit_total_pass = in.readString();
		revisit_type = in.readString();
		revisit_general_amount = in.readString();
		revisit_consulation_charges = in.readString();
		revisit_internet_charges = in.readString();
		revisit_discount = in.readString();
		revisit_total_amount = in.readString();
		revisit_total_pass = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public String toString() {
		super.toString();
		return "newvisit_type : " + newvisit_type + "\n"
				+ "newvisit_general_amount : " + newvisit_general_amount + "\n"
				+ "newvisit_consulation_charges : "
				+ newvisit_consulation_charges + "\n"
				+ "newvisit_internet_charges : " + newvisit_internet_charges
				+ "\n" + "newvisit_discount : " + newvisit_discount + "\n"
				+ "newvisit_total_amount : " + newvisit_total_amount + "\n"
				+ "newvisit_total_pass : " + newvisit_total_pass + "\n"
				+ "revisit_type : " + revisit_type + "\n"
				+ "revisit_general_amount : " + revisit_general_amount + "\n"
				+ "revisit_consulation_charges : "
				+ revisit_consulation_charges + "\n"
				+ "revisit_internet_charges : " + revisit_internet_charges
				+ "\n" + "revisit_discount : " + revisit_discount + "\n"
				+ "revisit_total_amount : " + revisit_total_amount + "\n"
				+ "revisit_total_pass : " + revisit_total_pass + "\n";
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public HCAmountHolder createFromParcel(Parcel in) {
			return new HCAmountHolder(in);
		}

		@Override
		public Object[] newArray(int size) {
			return new HCAmountHolder[size];
		}
	};
}
