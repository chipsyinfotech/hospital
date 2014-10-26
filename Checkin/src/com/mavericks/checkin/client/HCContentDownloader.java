/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HCContentDownloader.java
 * @Project:
 *		 checkin
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.security.KeyStore;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import com.mavericks.checkin.HCCheckinAppplication;
import com.mavericks.checkin.R;
import com.mavericks.checkin.holders.HCNameValuePair;
import com.mavericks.checkin.utils.HCConstants;
import com.mavericks.checkin.utils.HCUtils;

public class HCContentDownloader {

	private DefaultHttpClient mDefaultHttpClient;
	private static final int CONCURRENCY = 10;
	private static final int CONNECTION_TIMEOUT = 30000; // 30 secs timeout
	private static final int SOCKET_TIMEOUT = 30000; // 30 secs timeout

	public HCContentDownloader() {
		// Initialize the thread safe http client - thread safe in this case
		HttpParams params = new BasicHttpParams();
		ConnPerRoute connPerRoute = new ConnPerRouteBean(CONCURRENCY);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		ConnManagerParams.setMaxTotalConnections(params, CONCURRENCY);
		ConnManagerParams.setTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);

		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 443));
		// schemeRegistry.register(new Scheme("https",
		// createAdditionalCertsSSLSocketFactory(), 443));

		ClientConnectionManager connectionManager = new ThreadSafeClientConnManager(
				params, schemeRegistry);

		mDefaultHttpClient = new DefaultHttpClient(connectionManager, params);
		mDefaultHttpClient.setParams(params);

	}

	private BufferedHttpEntity requestGet(String url, boolean retry)
			throws ClientProtocolException, IOException, ServerNotAccessible {
		try {

			BufferedHttpEntity bufHttpEntity = null;
			HttpResponse response = mDefaultHttpClient
					.execute(getHttpGetRequest(url));
			int status = response.getStatusLine().getStatusCode();

			if (isValidStatus(status)) {
				HttpEntity entity = response.getEntity();
				bufHttpEntity = new BufferedHttpEntity(entity);
				entity = null;
			} else {
				throw new ServerNotAccessible(url, status);
			}
			return bufHttpEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerNotAccessible(url, ServerNotAccessible.DefErrCode);

		}
	}

	protected org.apache.http.conn.ssl.SSLSocketFactory createAdditionalCertsSSLSocketFactory() {
		try {
			final KeyStore ks = KeyStore.getInstance("BKS");

			// the bks file we generated above
			final InputStream in = HCCheckinAppplication.getContext()
					.getResources().openRawResource(R.raw.server);
			try {
				// don't forget to put the password used above in
				// strings.xml/mystore_password
				ks.load(in, "password".toCharArray());
			} finally {
				in.close();
			}

			return new HCAdditionalKeyStoresSSLSocketFactory(ks);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private BufferedHttpEntity requestPost(String url,
			List<HCNameValuePair> form_data, boolean retry)
			throws ClientProtocolException, IOException, ServerNotAccessible {

		try {
			BufferedHttpEntity bufHttpEntity = null;

			HttpResponse response = mDefaultHttpClient.execute(getPostRequest(
					url, form_data));
			int status = response.getStatusLine().getStatusCode();
			HCUtils.Log(" --------STATUS  "+status);
			if (isValidStatus(status)) {
				HttpEntity entity = response.getEntity();
				bufHttpEntity = new BufferedHttpEntity(entity);
				entity = null;
			} else {
				throw new ServerNotAccessible(url, status);
			}
			return bufHttpEntity;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerNotAccessible(url, ServerNotAccessible.DefErrCode);
		}
	}

	private BufferedHttpEntity requestPut(String url,
			List<HCNameValuePair> form_data, boolean retry)
			throws ClientProtocolException, IOException, ServerNotAccessible {

		try {
			BufferedHttpEntity bufHttpEntity = null;

			HttpResponse response = mDefaultHttpClient.execute(getPutRequest(
					url, form_data));
			int status = response.getStatusLine().getStatusCode();
			if (isValidStatus(status)) {
				HttpEntity entity = response.getEntity();
				bufHttpEntity = new BufferedHttpEntity(entity);
				entity = null;
			} else {
				throw new ServerNotAccessible(url, status);
			}
			return bufHttpEntity;
		} catch (SocketException e) {

			throw new ServerNotAccessible(url, ServerNotAccessible.DefErrCode);

		}
	}

	/**
	 * get the server response in the form of inputstream
	 * 
	 * @param url
	 * @return
	 * @throws ServerNotAccessible
	 */
	public InputStream getInputStreamPOST(String url,
			List<HCNameValuePair> form_data) throws ServerNotAccessible {

		BufferedHttpEntity bufferedEntity = null;
		InputStream is = null;
		try {
			bufferedEntity = requestPost(url, form_data, false);
			if (bufferedEntity != null) {
				is = bufferedEntity.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerNotAccessible e) {
			throw e;
		} finally {
			try {
				if (bufferedEntity != null) {
					bufferedEntity.consumeContent();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (url != null) ? is : null;
	}

	/**
	 * get the server response in the form of inputstream
	 * 
	 * @param url
	 * @return
	 * @throws ServerNotAccessible
	 */
	public InputStream getInputStreamGET(String url) throws ServerNotAccessible {
		BufferedHttpEntity bufferedEntity = null;
		InputStream is = null;
		try {

			bufferedEntity = requestGet(url, false);
			if (bufferedEntity != null) {
				is = bufferedEntity.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerNotAccessible e) {
			throw e;
		} finally {
			try {
				if (bufferedEntity != null) {
					bufferedEntity.consumeContent();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (url != null) ? is : null;
	}

	public InputStream getInputStreamPUT(String url,
			List<HCNameValuePair> form_data) throws ServerNotAccessible {

		BufferedHttpEntity bufferedEntity = null;
		InputStream is = null;
		try {
			bufferedEntity = requestPut(url, form_data, false);
			if (bufferedEntity != null) {
				is = bufferedEntity.getContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServerNotAccessible e) {
			throw e;
		} finally {
			try {
				if (bufferedEntity != null) {
					bufferedEntity.consumeContent();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return (url != null) ? is : null;
	}

	private boolean isValidStatus(int status) {
		if (status == HttpStatus.SC_OK || status == HttpStatus.SC_NO_CONTENT)
			return true;
		else
			return false;

	}

	/**
	 * returns multipart entity for fileupload
	 * 
	 * @param form_data
	 * @return
	 */
	private MultipartEntityBuilder getMulitpartEntity(
			List<NameValuePair> form_data) {
		MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();

		for (NameValuePair pair : form_data) {

			if (pair.getName().equalsIgnoreCase(HCConstants.MULTIPART_FILE_KEY)) {

				reqEntity.addPart(String.valueOf(pair.getName()), new FileBody(
						new File(pair.getValue())));

			} else {
				reqEntity
						.addPart(pair.getName(), new StringBody(
								pair.getValue(), ContentType.TEXT_PLAIN));
			}

		}
		return reqEntity;
	}

	private boolean isFileUpload(List<HCNameValuePair> form_data) {
		boolean isFileUpload = false;
		if (form_data != null) {

			for (HCNameValuePair pair : form_data) {
				if (pair.getName().equalsIgnoreCase(
						HCConstants.MULTIPART_FILE_KEY))
					isFileUpload = true;
			}
		}

		return isFileUpload;
	}

	private boolean requiresUTF8(String Url) {
		boolean requiresUTF8 = false;

		if (Url.contains(HCServerUtils.PATH_TEXT_MESSAGE))
			requiresUTF8 = true;

		return requiresUTF8;
	}

	// Generic post request generator adding the headers.
	private HttpPost getPostRequest(String url, List<HCNameValuePair> form_data) {

		HttpPost post = new HttpPost(url);
		post.setHeader("Accept", "application/json");
		post.setHeader("Cache-Control", "no-cache");
		post.setHeader("Cache-Control", "max-age=0");

		if (form_data != null) {
			if (isFileUpload(form_data)) {

			//	post.setEntity(getMulitpartEntity(form_data).build());

			} else {
				if (requiresUTF8(url)) {
					try {
						post.setEntity(new UrlEncodedFormEntity(form_data,
								HTTP.UTF_8));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				} else {

					try {
						post.setEntity(new UrlEncodedFormEntity(form_data,
								HTTP.UTF_8));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return post;
	}

	private HttpPut getPutRequest(String url, List<HCNameValuePair> form_data) {

		HttpPut put = new HttpPut(url);

		put.setHeader("Accept", "application/json");
		put.setHeader("Cache-Control", "no-cache");
		put.setHeader("Cache-Control", "max-age=0");

		if (isFileUpload(form_data)) {

		//	put.setEntity(getMulitpartEntity(form_data).build());

		} else {
			if (requiresUTF8(url)) {
				try {
					put.setEntity(new UrlEncodedFormEntity(form_data,
							HTTP.UTF_8));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else if (form_data != null) {
				try {
					put.setEntity(new UrlEncodedFormEntity(form_data,
							HTTP.UTF_8));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}

		return put;
	}

	// Generic get request generator adding the headers.
	private HttpGet getHttpGetRequest(String url) {
		HttpGet get = new HttpGet(url);

		get.addHeader(HTTP.USER_AGENT, System.getProperty("http.agent"));
		get.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
		get.setHeader("Accept", "application/json");
		get.setHeader("Cache-Control", "no-cache");
		get.setHeader("Cache-Control", "max-age=0");
		return get;
	}

	public class ServerNotAccessible extends Exception {

		private static final int DefErrCode = 12345;

		public ServerNotAccessible(String string, int errCode) {
			String msg = "";
		}
	}

}
