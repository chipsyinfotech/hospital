package com.mavericks.checkin.holders;

import android.os.Parcel;
import android.os.Parcelable;

public class HCLocationHolder extends HCBaseHolder implements Parcelable {

    	 
    String mHosLocation;
	String mHosId;
	String mHosName;
	String mHosAddr;
	String mHosMobile;
	int mPriorityPass;
	int mCashOnArrival;
	
	public String getmHosLocation() {
		return mHosLocation;
	}
	public void setmHosLocation(String mHosLocation) {
		this.mHosLocation = mHosLocation;
	}
	public String getmHosId() {
		return mHosId;
	}
	public void setmHosId(String mHosId) {
		this.mHosId = mHosId;
	}
 public HCLocationHolder() {
		// TODO Auto-generated constructor stub
	}	
	

	public HCLocationHolder(Parcel in) {
		readFromParcel(in);
	}
	public String getmHosName() {
		return mHosName;
	}
	public void setmHosName(String mHosName) {
		this.mHosName = mHosName;
	}
	public String getmHosAddr() {
		return mHosAddr;
	}
	public void setmHosAddr(String mHosAddr) {
		this.mHosAddr = mHosAddr;
	}
	public String getmHosMobile() {
		return mHosMobile;
	}
	public void setmHosMobile(String mHosMobile) {
		this.mHosMobile = mHosMobile;
	}
	public int getmPriorityPass() {
		return mPriorityPass;
	}
	public void setmPriorityPass(int mPriorityPass) {
		this.mPriorityPass = mPriorityPass;
	}
	public int getmCashOnArrival() {
		return mCashOnArrival;
	}
	public void setmCashOnArrival(int mCashOnArrival) {
		this.mCashOnArrival = mCashOnArrival;
	}
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mHosLocation);
		dest.writeString(mHosId);
		dest.writeString(mHosName);
		dest.writeString(mHosAddr);
		dest.writeString(mHosMobile);
		dest.writeInt(mPriorityPass);
		dest.writeInt(mCashOnArrival);
		

	
	
		
	}

	public void readFromParcel(Parcel in) {
		mHosLocation = in.readString();
		mHosId = in.readString();
		mHosName = in.readString();
		mHosAddr = in.readString();
		mHosMobile = in.readString();
		mPriorityPass = in.readInt();
		mCashOnArrival = in.readInt();
		
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		@Override
		public HCLocationHolder createFromParcel(Parcel in) {
			return new HCLocationHolder(in);
		}

		@Override
		public Object[] newArray(int size) {
			return new HCLocationHolder[size];
		}
	};

	@Override
	public String toString() {	
		super.toString();
		return "mHosLocation : "+mHosLocation +"\n"+
				"mHosId : "+mHosId+"\n"+
				"mHosName"+mHosName+"\n"+
				"mHosAddr"+mHosAddr+"\n"+
				"mHosMobile"+mHosMobile+"\n"+
				"mPriorityPass"+mPriorityPass+"\n"+
				"mCashOnArrival"+mCashOnArrival+"\n";
	}
}
