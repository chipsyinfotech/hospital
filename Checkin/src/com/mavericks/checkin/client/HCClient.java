/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCClient.java
 * @Project:
 *		 Checkin
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2014, 101 Mavericks.
 *		Written under contract by Chipsy Information Technology.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 22-Aug-2014
 */
package com.mavericks.checkin.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;

import android.content.Context;
import android.os.AsyncTask;

import com.mavericks.checkin.R;
import com.mavericks.checkin.client.HCContentDownloader.ServerNotAccessible;
import com.mavericks.checkin.parser.HCBaseJsonParser;
import com.mavericks.checkin.utils.HCAsyncTaskUtils;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

/**
 * @author Vijayalaxmi
 * 
 */
public class HCClient {

    protected static final int SET_IMAGE = 111;
    public static final int REQ_TYPE_GET = 1;
    public static final int REQ_TYPE_POST = 2;
    public static final int REQ_TYPE_PUT = 3;
    private static HCClient mCheckinClient;
    private HCContentDownloader mContentDownloader = new HCContentDownloader();
    private ArrayList<FileDownloader> mTaskList = new ArrayList<FileDownloader>();
    FileDownloader mSmartUrlDwnldr;
   
    Context mContext;
    

    /**
     * To get the singleton object of the client.
     * 
     * @return single instance object of ABClient
     */
    public static HCClient getInstance() {
	if (mCheckinClient == null) {
		mCheckinClient = new HCClient();
	}
	return mCheckinClient;
    }


    public void request(final Context context, int ReqType,
	    List<NameValuePair> urlFields, List<NameValuePair> formData,
	    List<NameValuePair> optionalData, final HCBaseJsonParser dataModel,
	    final HCIRequestListener IRequestListener) {
	
	process(context, ReqType, urlFields, formData, optionalData, dataModel,
		IRequestListener);
    }

    public void process(final Context context, int ReqType,
	    List<NameValuePair> urlFields, List<NameValuePair> formData,
	    List<NameValuePair> optional, final HCBaseJsonParser dataModel,
	    final HCIRequestListener IRequestListener) {

	if (!HCUtils.isInternetOn(context)) {
	    if(null != dataModel)
	    dataModel.setStatusMsg(HCConstants.ERROR_CODE_NO_NETWORK, "");
	    IRequestListener.onComplete(ReqType,
		    HCConstants.ERROR_CODE_NO_NETWORK);
	    return;
	}

	if (formData != null)
	    HCUtils.Log("URL", " Get formData: " + formData.toString());
	
	if (urlFields != null)
	    HCUtils.Log("URL", " Get urlFields: " + urlFields.toString());

	if (optional != null)
	    HCUtils.Log("URL", " Get optionalFields: " + optional.toString());

	String mUrl = HCServerUtils.getURL(ReqType);

	// Append params to the URL if the Http Request is of Type GET
	if (urlFields != null)
	    mUrl = mUrl + getUrlPathForGet(urlFields);

	// adds Optional fields to the Url
	if (optional != null)
	    mUrl = mUrl + "?" + URLEncodedUtils.format(optional, "utf-8");

	HCUtils.Log("URL", "FINAL  url: " + mUrl);

	// Downloads the contents from the server
	downloadContents(context, mUrl, formData, ReqType, IRequestListener,
		dataModel);
    }
    
    /****************************************************************/
    
    public void request(final Context context, int ReqType, String url,
	    List<NameValuePair> urlFields, List<NameValuePair> formData,
	    List<NameValuePair> optionalData, final HCBaseJsonParser dataModel,
	    final HCIRequestListener IRequestListener) {
	
	process(context, ReqType, urlFields, formData, optionalData, dataModel,
		IRequestListener);
    }

    public void process(final Context context, int ReqType,String url,
	    List<NameValuePair> urlFields, List<NameValuePair> formData,
	    List<NameValuePair> optional, final HCBaseJsonParser dataModel,
	    final HCIRequestListener IRequestListener) {

	if (!HCUtils.isInternetOn(context)) {
	    if(null != dataModel)
	    dataModel.setStatusMsg(HCConstants.ERROR_CODE_NO_NETWORK, "");
	    IRequestListener.onComplete(ReqType,
		    HCConstants.ERROR_CODE_NO_NETWORK);
	    return;
	}

	if (formData != null)
	    HCUtils.Log("URL", " Get formData: " + formData.toString());
	
	if (urlFields != null)
	    HCUtils.Log("URL", " Get urlFields: " + urlFields.toString());

	if (optional != null)
	    HCUtils.Log("URL", " Get optionalFields: " + optional.toString());

	String mUrl = url;

	// Append params to the URL if the Http Request is of Type GET
	if (urlFields != null)
	    mUrl = mUrl + getUrlPathForGet(urlFields);

	// adds Optional fields to the Url
	if (optional != null)
	    mUrl = mUrl + "?" + URLEncodedUtils.format(optional, "utf-8");

	HCUtils.Log("URL", "FINAL  url: " + mUrl);

	// Downloads the contents from the server
	downloadContents(context, mUrl, formData, ReqType, IRequestListener,
		dataModel);
    }
    
    
    /**
     * cancels all the tasks related to previous screen
     */
    public void CancelPrevScreentasks() {
	for (FileDownloader t : mTaskList) {
	    t.cancel(true);
	}
    }

    /**
     * Downloads the contents and parses the Json Response
     * 
     * @param context
     * @param mUrl
     * @param urlFields
     * @param ReqType
     * @param hUMIRequestListener
     * @param dataModel
     */
    private void downloadContents(final Context context, String mUrl,
	    List<NameValuePair> formData, final int ReqType,
	    final HCIRequestListener hUMIRequestListener,
	    final HCBaseJsonParser dataModel) {
	// Downloads the contents from the server
	FileDownloader fileDownloader = new FileDownloader(mUrl, formData,
		HCServerUtils.getRequestType(ReqType),
		new IDownloadListener() {

		    @Override
		    public <T> void onComplete(int status, InputStream response) {
			mTaskList.remove(this);
			if (status == HCConstants.ERROR_CODE_SUCCESS) {
			    if(null != dataModel){
				parseResponseModel(context, response, dataModel,
				    ReqType, hUMIRequestListener);
			    }
			} else {
			    HCUtils.Log("FileDownloader failed: " + status
				    + " Req type:" + ReqType);
			    if(null != dataModel)
			    dataModel.setStatusMsg(
				    status,
				    context.getResources().getString(
					    R.string.download_error));
			    hUMIRequestListener.onComplete(ReqType, status);
			}
		    }
		});

	// checks if duplicate request are made and cancels if exists
	if (!mTaskList.contains(fileDownloader)) {
	    mTaskList.add(fileDownloader);
	    HCAsyncTaskUtils.execute(fileDownloader, null);
	} else
	    fileDownloader.cancel(true);
    }

   

    /**
     * Cancels all the previous tasks
     */

    public void cancelPreviousTasks() {
	for (FileDownloader t : mTaskList) {
	    t.cancel(true);
	}
    }

    private String getUrlPathForGet(List<NameValuePair> urlFields) {
	String path = "";

	if (urlFields != null) {

	    for (NameValuePair pair : urlFields) {
		path = path + "/" + pair.getValue();
	    }
	}
	HCUtils.Log("Path for get :" + path);
	return path;
    }

    /**
     * AsyncTask to handle feed downloads.
     */

    private class FileDownloader extends AsyncTask<Void, Void, InputStream> {
	String mUrl;
	IDownloadListener mDownloadListener;
	int mReqType;
	boolean serverInaccessible = false;
	List<NameValuePair> mForm_data;
	public FileDownloader(String url, List<NameValuePair> form_data,
		int reqType, IDownloadListener listener) {
	    mUrl = url;
	    mDownloadListener = listener;
	    mReqType = reqType;
	    mForm_data = form_data;
	}

	@Override
	protected InputStream doInBackground(Void... params) {
	    InputStream document = null;

	    try {
		if (mReqType == HCClient.REQ_TYPE_POST)
		    document = mContentDownloader.getInputStreamPOST(mUrl,
			    mForm_data);
		else if (mReqType == HCClient.REQ_TYPE_GET)
		    document = mContentDownloader.getInputStreamGET(mUrl);
		else if (mReqType == HCClient.REQ_TYPE_PUT)
		    document = mContentDownloader.getInputStreamPUT(mUrl,
			    mForm_data);

	    } catch (ServerNotAccessible e) {
		e.printStackTrace();
		serverInaccessible = true;
	    }
	    return document;
	}

	@Override
	protected void onPostExecute(InputStream result) {
	    HCUtils.Log("*******Samrt url onPostExecute");
	   
	    if (!isCancelled()) {
		if (!serverInaccessible) {
		    if (result != null) {
			mDownloadListener.onComplete(
				HCConstants.ERROR_CODE_SUCCESS, result);
		    } else {
			mDownloadListener.onComplete(
				HCConstants.ERROR_CODE_SERVER_NOT_ACCESSIBLE,
				result);
		    }
		} else
		    mDownloadListener.onComplete(
			    HCConstants.ERROR_CODE_SERVER_NOT_ACCESSIBLE,
			    result);
	    }
	   
	}

	@Override
	protected void onCancelled() {
	    HCUtils.Log("Cancelled url: " + mUrl);
	    super.onCancelled();
	}
    }

    /**
     * parses the responses into the given data model.
     * 
     * @param response
     *            Inputstream from the response entity.
     * @param dataModel
     *            The data model to be parsed into.
     * @param hUMIRequestListener
     *            Listener to callback on completion.
     */
    protected void parseResponseModel(final Context c, InputStream response,
	    final HCBaseJsonParser dataModel, final int ReqType,
	    final HCIRequestListener IRequestListener) {
	ModelParserTask task = new ModelParserTask(response, dataModel, new IJSONParseListener() {

	    @Override
	    public <T> void onComplete(int status) {
		if (status == HCConstants.ERROR_CODE_SUCCESS) {
		    IRequestListener.onComplete(ReqType,
		    		dataModel.getStatusErrorCode());
		} else {
		    // TODO check possible reasons for parsing failure and
		    // handle it.
		    IRequestListener.onComplete(ReqType,
			    HCConstants.ERROR_CODE_SERVER_NOT_ACCESSIBLE);
		    HCUtils.Log(" Parsing failed status: " + status);
		}
	    }
	});
		HCAsyncTaskUtils.execute(task, null); 
    }

    // AsyncTasks invoking ResponseParsers.
    /**
     * AsyncTask for handling parsers.
     * 
     */
    private class ModelParserTask extends AsyncTask<Void, Void, Integer> {
	InputStream document;
	HCBaseJsonParser model;
	IJSONParseListener statusListener;

	public ModelParserTask(InputStream response,
		HCBaseJsonParser userModel, IJSONParseListener iDownloadListener) {
	    document = response;
	    model = userModel;
	    statusListener = iDownloadListener;
	}

	@Override
	protected Integer doInBackground(Void... params) {
	    Integer status = HCConstants.ERROR_CODE_SUCCESS;
	    try {
		model.initialize(getResponseString(document));
		return model.getStatusErrorCode();
	    } catch (IOException e) {
		status = HCConstants.ERROR_CODE_IO_EXCEPTION;
		e.printStackTrace();
	    } catch (JSONException e) {
		status = HCConstants.ERROR_CODE_JSON_EXCEPTION;
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
		status = HCConstants.ERROR_CODE_EXCEPTION;
	    }
	    return status;
	}

	@Override
	protected void onPostExecute(Integer status) {
	    if (statusListener != null && !isCancelled())
		statusListener.onComplete(status);
	    try {
		document.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	private StringBuilder getResponseString(InputStream stream)
		throws IOException {

	    BufferedReader bufferedReader = new BufferedReader(
		    new InputStreamReader(stream), 16 * 1024);
	    String line;
	    StringBuilder buffer = new StringBuilder();

	    while ((line = bufferedReader.readLine()) != null) {
		buffer.append(line);
	    }
	    
	    HCUtils.Log("RESPONCE", "Responce:" + buffer);
	    return buffer;
	}
    }

    // Listener classes defined

    public interface IDownloadListener {

	public <T> void onComplete(int status, InputStream document);
    }

    public interface IJSONParseListener {

	public <T> void onComplete(int status);
    }

}