/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		ABImageDownloader.java
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
package com.mavericks.checkin.client;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.androidquery.callback.ImageOptions;
import com.androidquery.util.AQUtility;
import com.mavericks.checkin.HCCheckinAppplication;
import com.mavericks.checkin.R;
import com.mavericks.checkin.utils.HCConstants;

public class HCImageDownloader {
	// static DisplayImageOptions options;
	public static final int THREADCOUNT = 8;
	public static final String IMG_PATH = "Android/data/"
			+ HCCheckinAppplication.PACKAGENAME + "/images";
	public static final String IMG_LARGE = "/large";
	public static final String IMG_SMALL = "/small";
	public static final String IMG_CEL_BG = "/celeb_bg";

	public static void clearAllImages() {

		long triggerSize = 3000000; // starts cleaning when cache size is larger
		// than 3M
		long targetSize = 2000000; // remove the least recently used files until
		// cache size is less than 2M

		ImgClearTask imgTask = new ImgClearTask(IMG_PATH, triggerSize,
				targetSize); // remove the least recently used files until
		imgTask.execute();

	}

	// public static void clearAllLargeImages1() {
	//
	// ImgClearTask imgTask = new ImgClearTask(IMG_PATH + IMG_LARGE, 0, 0);
	// imgTask.execute();
	// }

	/**
	 * clear all large images within target size
	 */
	public static void clearLargeImages() {

		long triggerSize = 8000000; // starts cleaning when cache size is larger
		// than 1M
		long targetSize = 1000000; // remove the least recently used files until
		// cache size is less than 2M

		ImgClearTask imgTask = new ImgClearTask(IMG_PATH + IMG_LARGE,
				triggerSize, targetSize);
		imgTask.execute();
	}

	/**
	 * clear all large images within target size
	 */
	public static void clearSmallImages() {

		long triggerSize = 8000000; // starts cleaning when cache size is larger
		// than 1M
		long targetSize = 1000000; // remove the least recently used files until
		// cache size is less than 2M

		ImgClearTask imgTask = new ImgClearTask(IMG_PATH + IMG_SMALL,
				triggerSize, targetSize);
		imgTask.execute();
	}

	public static class ImgClearTask extends AsyncTask<Void, Void, Void> {

		String path;
		long mTrig;
		long mTarget;

		/**
	 * 
	 */
		public ImgClearTask(String imgPath, long trig, long target) {
			path = imgPath;
			mTrig = trig;
			mTarget = target;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(java.lang.Object[])
		 */
		@Override
		protected Void doInBackground(Void... params) {
			File ext = Environment.getExternalStorageDirectory();
			File cacheDir = new File(ext, IMG_PATH + IMG_LARGE);
			AQUtility.cleanCache(cacheDir, mTrig, mTarget);
			return null;
		}

	}

	public static void initAquery() {

		// set the max number of concurrent network connections, default is 4
		AjaxCallback.setNetworkLimit(8);

		// set the max number of icons (image width <= 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setIconCacheLimit(20);

		// set the max number of images (image width > 50) to be cached in
		// memory, default is 20
		BitmapAjaxCallback.setCacheLimit(10);

		// set the max size of the memory cache, default is 1M pixels (4MB)
		BitmapAjaxCallback.setMaxPixelLimit(2000000);

		// File ext = Environment.getExternalStorageDirectory();
		// File cacheDir = new File(ext, "Stardom");
		// AQUtility.setCacheDir(cacheDir);
	}

	/**
	 * reads the dimensions from the value folder
	 * 
	 * @param context
	 * @param Type
	 * @return
	 */
	private static float[] getImageDimensions(Context context, int Type) {
		float[] size = new float[2];
		switch (Type) {
		 case HCConstants.IMG_PROFILE:
		 size = getDimensinDp(context, R.dimen.img_sz_prof,
		 R.dimen.img_sz_prof);
		 break;
		}

		return size;
	}

	private static int getImgPadding(Context context, int size) {

		float padding = 6;
		switch (size) {
		  case HCConstants.IMG_PROFILE:
			  padding = context.getResources().getDimension(R.dimen.margin_s);
		 break;

		}
		// DisplayMetrics displaymetrics = new DisplayMetrics();
		// ((Activity) context).getWindowManager().getDefaultDisplay()
		// .getMetrics(displaymetrics);
		return (int) ((int) padding);// * displaymetrics.density);
	}

	private static float[] getDimensinDp(Context context, int widhtDimenId,
			int heightDimenId) {

		float[] size = new float[2];
		size[0] = context.getResources().getDimension(widhtDimenId);
		size[1] = context.getResources().getDimension(heightDimenId);

		return size;
	}

	/**
	 * sets the layout params to the imageView
	 * 
	 * @param context
	 * @param imageView
	 * @param type
	 */
	private static void setImageViewParams(Context context,
			ImageView imageView, int type) {
		float size[] = getImageDimensions(context, type);
		LayoutParams paramImage = imageView.getLayoutParams();
		paramImage.width = (int) size[0];
		paramImage.height = (int) size[1];
		imageView.setLayoutParams(paramImage);
	}

	private static String getDomailWithUrl(String url) {
		if (url == null)
			return "";

		return url;
	}

	/**
	 * For Image Views Only
	 * 
	 * @param view
	 * @param url
	 */
	private static void downloadCircularImage(View view, String url,
			int defimg, boolean isLarge) {

		String imgPath;

		if (isLarge)
			imgPath = IMG_PATH + IMG_LARGE;
		else
			imgPath = IMG_PATH + IMG_SMALL;

		File ext = Environment.getExternalStorageDirectory();
		File cacheDir = new File(ext, imgPath);
		AQUtility.setCacheDir(cacheDir);

		url = getDomailWithUrl(url);
		final AQuery aq = new AQuery(view);
		aq.image(url, false, true, 0, defimg, new BitmapAjaxCallback() {
			public void callback(String url, ImageView iv, Bitmap bm,
					AjaxStatus status) {

				ImageOptions options = new ImageOptions();
				options.round = 1000;
				aq.image(url, options);
			}
		});

	}

	/**
	 * For Image Views Only
	 * 
	 * @param view
	 * @param url
	 */
	private static void downloadSquareImage(View view, String url,
			final int defimg, final boolean isLarge, final boolean isfitImage) {

		String imgPath;

		if (isLarge) {
			imgPath = IMG_PATH + IMG_LARGE;
			clearLargeImages();
		} else {
			imgPath = IMG_PATH + IMG_SMALL;
			clearSmallImages();
		}

		File ext = Environment.getExternalStorageDirectory();
		File cacheDir = new File(ext, imgPath);
		AQUtility.setCacheDir(cacheDir);

		url = getDomailWithUrl(url);
		final AQuery aq = new AQuery(view);
		aq.image(url, false, true, 0, 0, new BitmapAjaxCallback() {
			@SuppressLint("NewApi")
			@Override
			public void callback(String url, final ImageView iv,
					final Bitmap bm, AjaxStatus status) {

				if (bm == null) {
					iv.setBackgroundResource(defimg);
					return;
				}

				if (isfitImage) {
					Matrix matrix = new Matrix();
					float scaleVal = (float) iv.getWidth()
							/ (float) bm.getWidth();

					// STAUtils.Log("bm: "+bm.getWidth()+" vw: "+iv.getWidth()+" sc: "+scaleVal);
					matrix.setScale(scaleVal, scaleVal);
					if (bm.getHeight() * scaleVal < iv.getHeight())
						matrix.postTranslate(0,
								(iv.getHeight() / 2 - (bm.getHeight()
										* scaleVal / 2)));
					else
						matrix.postTranslate(0, 0);
					iv.setImageMatrix(matrix);
					iv.setScaleType(ScaleType.MATRIX);
				}
				if (!isLarge)
					iv.setBackgroundColor(Color.BLACK);
				iv.setImageBitmap(bm);

			}
		});

	}

	public static void setSquareImg(ImageView imgvw, String url, int defimg,
			boolean isLarge, boolean bFitImage) {
		imgvw.setBackgroundResource(defimg);
		// check for empty url
		if (TextUtils.isEmpty(url)) {
			return;
		}
		// download circular image from server
		downloadSquareImage(imgvw, url, defimg, isLarge, bFitImage);
	}

	public static void setSquareImg(Context con, ImageView imgvw, String url,
			int size, int defimg, boolean isLarge, boolean bFitImage) {
		url = getDomailWithUrl(url);
		setImageViewParams(con, imgvw, size);
		imgvw.setBackgroundResource(defimg);
		// check for empty url
		if (TextUtils.isEmpty(url)) {
			return;
		}
		// download circular image from server
		downloadSquareImage(imgvw, url, defimg, isLarge, bFitImage);
	}

	public static void setCircularImg(ImageView imgvw, String url, int defimg,
			boolean isLarge) {
		// check for empty url
		if (TextUtils.isEmpty(url)) {
			return;
		}
		// download circular image from server
		downloadCircularImage(imgvw, url, defimg, isLarge);
	}

	public static void setCircularImg(Context con, ImageView imgvw, String url,
			int size, int defimg, boolean islarge) {
		// set image view params
		setImageViewParams(con, imgvw, size);
		// set default image
		imgvw.setBackgroundResource(defimg);
		// set padding for circular image
		int padding = getImgPadding(con, size);
		imgvw.setPadding(padding, padding, padding, padding);
		// check for empty url
		if (TextUtils.isEmpty(url)) {
			return;
		}
		// download circular image from server
		downloadCircularImage(imgvw, url, defimg, islarge);
	}

}