package com.mavericks.checkin.holders;

import android.os.Parcel;
import android.os.Parcelable;

public class HCHistoryHolder extends HCBaseHolder implements Parcelable {
	String transaction_id;
	String user_name;
	String dob;
	String gender;
	String address;
	String fname;
	String hnumber;
	String email;
	String mobile;
	String special;
	String name;
	String visit_date;
	String dname;
	String totalamount;
	boolean priorpass = false;

	public String getTransaction_id() {
		return transaction_id;
	}

	public boolean isPriorpass() {
		return priorpass;
	}

	public void setPriorpass(boolean priorpass) {
		this.priorpass = priorpass;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public HCHistoryHolder() {	
	}

	public HCHistoryHolder(Parcel in) {
		readFromParcel(in);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getHnumber() {
		return hnumber;
	}

	public void setHnumber(String hnumber) {
		this.hnumber = hnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisit_date() {
		return visit_date;
	}

	public void setVisit_date(String visit_date) {
		this.visit_date = visit_date;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(String totalamount) {
		this.totalamount = totalamount;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(transaction_id);
		dest.writeString(user_name);
		dest.writeString(name);
		dest.writeString(special);
		dest.writeString(address);
		dest.writeString(visit_date);
		dest.writeString(totalamount);
		dest.writeString(visit_date);
		dest.writeString(dob);

	
	
		// dest.writeLong(favTime);
	}

	public void readFromParcel(Parcel in) {
		transaction_id = in.readString();
		user_name = in.readString();
		name = in.readString();
		special = in.readString();
		address = in.readString();
		visit_date = in.readString();
		totalamount = in.readString();
		visit_date = in.readString();
		dob = in.readString();
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public HCHistoryHolder createFromParcel(Parcel in) {
			return new HCHistoryHolder(in);
		}

		@Override
		public Object[] newArray(int size) {
			return new HCHistoryHolder[size];
		}
	};
}
