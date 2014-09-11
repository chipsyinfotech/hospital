/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABAlertManager.java
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
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.mavericks.checkin.utils.HCRetryDialog.OnRetryCancelClickListener;
import com.mavericks.checkin.utils.HCRetryDialog.OnRetryClickListener;

public class HCAlertManager {
    // Alert with Message , Without tile and Ok Button
    public static final int ALERT_TYPE__OK_BTN_MSG = 100;
    // Alert with Message , title and Ok Button
    public static final int ALERT_TYPE_OK_BTN_TITLE = 101;
    // Alert with Message Positive and cancel Button
    public static final int ALERT_TYPE_CANCEL_BTN_MSG = 102;
    // Alert with Message , title Positive and cancel Button
    public static final int ALERT_TYPE_CANCEL_BTN_TITLE = 103;

    public static final int ALERT_TYPE_NO_INTERNET = 104;

    public static interface IAlertButtonsListner {
	public void onPositiveClicked();

	public void onNegativeClicked();
    }
    
    public static interface IAlertDismissListner {
	public void onDissmiss();
    }
    
    public interface IRetryListner {
	public void onRetryClicked(int req);
    }

   

    public static void handleErrCode(Context context, int errCode,
	    int alertType, IAlertButtonsListner listner) {
	if (context == null)
	    return;

	String title = ""; // Title of the Alert
	String msg = ""; // Message of Alert
	String posBtnTxt = ""; // Positive Button Text
	switch (errCode) {
	case 100:
	    msg = "pirki not working";
	    break;

	default:
	    msg = " DEFAULT ERROR ";
	    break;
	}

	// show appropriate dialog
	switch (alertType) {
	case ALERT_TYPE__OK_BTN_MSG:
	    showAlertWithOneBtn(context, msg, listner);
	    break;

	case ALERT_TYPE_OK_BTN_TITLE:
	    showAlertWithOneBtn(context, title, msg, listner);
	    break;

	case ALERT_TYPE_CANCEL_BTN_MSG:
	    showAlertWithTwoBtn(context, msg, posBtnTxt, listner);
	    break;

	case ALERT_TYPE_CANCEL_BTN_TITLE:
	    showAlertWithTwoBtn(context, title, msg, posBtnTxt, listner);
	    break;
	}
    }

    /**
     * Alert with Message and Ok Button, Without title & listener
     * 
     * @param context
     * @param message
     */
    public static void showAlertWithOneBtnMsg(Context context, String message) {
	if (context == null)
	    return;

	showAlertWithOneBtnMsg(context, message, null);
    }
    
    /**
     * Alert to show No Internet message. With custom view,Title,Message, Two
     * buttons.
     * 
     * @param context
     */
    public static void showNoInternet(final Context context) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);

	errorDialog.setViewForDialog(HCErrorDialog.TWO_BTN_VIEW);
	errorDialog
		.setTitleForDialog(context.getString(R.string.network_error));
	// errorDialog.setMessageForDialog(context.getString(R.string.network_error));
	errorDialog.setPosBtnLabel(HCErrorDialog.SETTINGS);

	showDialog(context, errorDialog);
    }

    /**
     * Alert to show No Internet message. With custom view,Title,Message, Two
     * buttons.
     * 
     * @param context
     */
    public static void showRetry(final Context context, int reqType,
	    OnRetryClickListener lis) {
	if (context == null)
	    return;

	HCRetryDialog errorDialog = new HCRetryDialog(context, reqType);

	errorDialog
		.setTitleForDialog(context.getString(R.string.network_error));

	errorDialog.setOnRetryClickListener(lis);
	errorDialog.show(
		((FragmentActivity) context).getSupportFragmentManager(),
		"RetryDialog");
    }
    /**
     * Alert to show No Internet message. With custom view,Title,Message, Two
     * buttons.
     * 
     * @param context
     */
    public static void showRetry(final Context context, int reqType,
	    OnRetryCancelClickListener lis) {
	if (context == null)
	    return;

	HCRetryDialog errorDialog = new HCRetryDialog(context, reqType);

	errorDialog
		.setTitleForDialog(context.getString(R.string.network_error));

	errorDialog.setOnRetryCancelClickListener(lis);
	errorDialog.show(
		((FragmentActivity) context).getSupportFragmentManager(),
		"RetryDialog");
    }

    /**
     * Alert with Message and Ok Button & its listener, Without title
     * 
     * @param context
     * @param message
     * @param lis
     */
    public static void showAlertWithOneBtnMsg(Context context, String message,
	    final IAlertButtonsListner lis) {
	if (context == null)
	    return;

	showAlertWithOneBtnMsg(context, message, lis,null); 
	
    }
    
    public static void showAlertWithOneBtnMsg(Context context, String message,
	    final IAlertButtonsListner lis,IAlertDismissListner dismissLst) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);
	errorDialog.setTitleForDialog(message);
	errorDialog.setIAlertListener(lis);
	errorDialog.setmAlertDissmiss(dismissLst);
	
	showDialog(context, errorDialog);
    }

    /**
     * Alert with Message,Ok Button & its listener, Without tile
     * 
     * @param context
     * @param message
     * @param lis
     */
    public static void showAlertWithOneBtn(Context context, String message,
	    final IAlertButtonsListner lis) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);
	if(TextUtils.isEmpty(message))
		message = context.getString(R.string.err_server_not_accessible);
	errorDialog.setTitleForDialog(message);
	errorDialog.setIAlertListener(lis);

	showDialog(context, errorDialog);
    }

    /**
     * Alert with Title,Message,Ok Button and its listener.
     * 
     * @param context
     * @param title
     * @param message
     * @param lis
     */
    private static void showAlertWithOneBtn(Context context, String title,
	    String message, final IAlertButtonsListner lis) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);
//	errorDialog.setTitleForDialog(title);
	errorDialog.setTitleForDialog(message);
	errorDialog.setIAlertListener(lis);

	showDialog(context, errorDialog);
    }
    
    public static void showSettingDlg(Context context){
    	if (context == null)
    	    return;
    	HCErrorDialog errorDialog = new HCErrorDialog(context);
    	errorDialog.setViewForDialog(HCErrorDialog.PLAIN_VIEW);
    	showDialog(context, errorDialog);
    }
   
    /**
     * Alert with Message, Positive & cancel Button and listener.
     * 
     * @param context
     * @param message
     * @param posBtnTxt
     * @param lis
     */

    public static void showAlertWithTwoBtn(Context context, String message,
	    String posBtnTxt, final IAlertButtonsListner lis) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);

	errorDialog.setViewForDialog(HCErrorDialog.TWO_BTN_VIEW);
	errorDialog.setTitleForDialog(message);
	errorDialog.setIAlertListener(lis);
	errorDialog.setPosBtnLabel(posBtnTxt);

	showDialog(context, errorDialog);
    }

    /**
     * Alert with Title,Message,Positive & cancel Button and listener.
     * 
     * @param context
     * @param title
     * @param message
     * @param posBtnTxt
     * @param lis
     */
    private static void showAlertWithTwoBtn(Context context, String title,
	    String message, String posBtnTxt, final IAlertButtonsListner lis) {
	if (context == null)
	    return;

	HCErrorDialog errorDialog = new HCErrorDialog(context);

	errorDialog.setViewForDialog(HCErrorDialog.TWO_BTN_VIEW);
	//errorDialog.setTitleForDialog(title);
	errorDialog.setTitleForDialog(message);
	errorDialog.setIAlertListener(lis);
	errorDialog.setPosBtnLabel(posBtnTxt);

	showDialog(context, errorDialog);
    }

    /**
     * To show the dialog.
     * 
     * @param context
     * @param errorDialog
     */
    public static void showDialog(final Context context, final HCErrorDialog errorDialog) {

	/*
	 * This condition is added to avoid error when AlertManager is used
	 * from components which are not FragmentActivity such as
	 * STASettingDlgFrag
	 */		
	if (context instanceof FragmentActivity)
	    try {
		errorDialog.show(((FragmentActivity) context)
			.getSupportFragmentManager(), "ErrorDialog");		
		
	    } catch (IllegalStateException e) {
		/**
		 * For twitter reply success dialog, 
		 * .show() gives illegal state exception
		 * hence to display it, using the 
		 * following workaround.
		 */
		if (!((FragmentActivity) context).isFinishing()) {
		    FragmentTransaction transaction = ((FragmentActivity) context)
			    .getSupportFragmentManager().beginTransaction();
		    transaction.add(errorDialog, "ErrorDialog");
		    transaction.commitAllowingStateLoss();
		} else
		    return;
		
	    }catch (Exception e) {
		e.printStackTrace();
		return;
	    }
	else{}
	   // Toast.makeText(context, errorDialog.getMsg(), Toast.LENGTH_SHORT)
		//    .show();
    }

}