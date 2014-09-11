
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABRetryDialog.java
 * @Project:
 *		 Abharan
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */

package com.mavericks.checkin.utils;

import com.mavericks.checkin.R;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class HCRetryDialog extends DialogFragment implements OnClickListener {

    private Context mContext;
    
    private Dialog mDialog;
    private TextView mTxtTitle;
  
    private Button mBtnRetry;
    private Button mBtnCancel; 
    
    private OnRetryClickListener mRetryListener;
    private OnRetryCancelClickListener mRetryCancelListener;
    
    private String mTitle = "";
    private String mMsg = "";
    private int mReqType;

    public HCRetryDialog(Context context) {
	mContext = context;
    }
    
    public HCRetryDialog(Context context, int reqType) {
	this(context);
	mContext = context;
	mReqType = reqType;
    }
    
    public void setTitleForDialog(String title){
	mTitle = title;
    }
    
    public void setMessageForDialog(String message){
	mMsg = message;
    }
    
    public void setOnRetryClickListener(OnRetryClickListener listener){
	mRetryListener = listener;
    }    
    
    public void setOnRetryCancelClickListener(OnRetryCancelClickListener listener){
	mRetryCancelListener = listener;
      }    
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

	mDialog = new Dialog(mContext, R.style.Theme_Dialog_No_Title);
	mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	
	mDialog.setContentView(R.layout.view_cust_alert_two_btn_dlg);
	
	mTxtTitle = (TextView) mDialog.findViewById(R.id.txt_title);
	mBtnRetry = (Button) mDialog.findViewById(R.id.btn_positive);
	mBtnCancel = (Button) mDialog.findViewById(R.id.btn_negative);
	
	mTxtTitle.setText(mContext.getString(R.string.err_server_not_accessible));
	
	mBtnRetry.setText("Retry");
	mBtnRetry.setOnClickListener(this);
	mBtnCancel.setOnClickListener(this);
	
	mDialog.show();

	return mDialog;
    }
    
    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
	
	switch (v.getId()) {

	case R.id.btn_positive:
	    if(mRetryListener!=null)
		mRetryListener.onRetryClick(mReqType);
	    
	    if(mRetryCancelListener!=null)
		mRetryCancelListener.onRetryClick(mReqType);
	    break;
	case R.id.btn_negative:
	    if(mRetryCancelListener!=null)
		mRetryCancelListener.onCancelClicked(mReqType);
	    break;
	}
        
        mDialog.dismiss();
    }
    
    public interface OnRetryClickListener{	
	public void onRetryClick(int requestType);
    }
    public interface OnRetryCancelClickListener{	
	public void onRetryClick(int requestType);
	public void onCancelClicked(int requestType);
    }
}
