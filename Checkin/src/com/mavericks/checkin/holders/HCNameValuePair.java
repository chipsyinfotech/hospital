package com.mavericks.checkin.holders;

import java.io.Serializable;

import org.apache.http.message.BasicNameValuePair;

import android.os.Parcel;
import android.os.Parcelable;

public class HCNameValuePair extends BasicNameValuePair implements Parcelable{
	String mName;
	String mValue;
	
	public HCNameValuePair(String name, String value) {
        super(name, value);
        mName = name;
        mValue = value;
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mName);
		dest.writeString(mValue);
	}
	
	public void readFromParcel(Parcel in) {
		mName = in.readString();
		mValue = in.readString();
	}
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public HCNameValuePair createFromParcel(Parcel in) {
			return new HCNameValuePair(in.readString(),in.readString());
		}

		@Override
		public Object[] newArray(int size) {
			return new HCNameValuePair[size];
		}
	};
}
