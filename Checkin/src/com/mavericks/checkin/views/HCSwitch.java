package com.mavericks.checkin.views;

import java.util.Hashtable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.CompoundButton;

import com.mavericks.checkin.R;

public class HCSwitch extends CompoundButton {

	int v_width = 0;
	int v_height = 0;

	private String mTextOn = null;
	private String mTextOff = null;
	private String mFontName = null;
	private float mTextSize = 10;

	private int mTextColor = 0xFFffffff;;

	private Bitmap mSwitchButton = null;

	private TextPaint mTextPaint = null;

	private boolean mIsDragging = false;

	public HCSwitch(Context context, AttributeSet attrs) {
		super(context, attrs);

		parse((context.obtainStyledAttributes(attrs, R.styleable.customSwitch)));
	}

	private void parse(TypedArray array) {

		mTextOn = array.getString(R.styleable.customSwitch_switch_on_text);
		mTextOff = array.getString(R.styleable.customSwitch_switch_off_text);
		mTextColor = array.getColor(R.styleable.customSwitch_text_color,
				mTextColor);
		mFontName = array.getString(R.styleable.customSwitch_font);
		mTextSize = array.getDimension(R.styleable.customSwitch_text_size,
				mTextSize);

		if (array.hasValue(R.styleable.customSwitch_image_src)) {
			Drawable drawable = array
					.getDrawable(R.styleable.customSwitch_image_src);

			if (mSwitchButton == null) {
				mSwitchButton = ((BitmapDrawable) drawable).getBitmap();
			}

			Log.d("setting", "switch button : " + mSwitchButton);
		}

		array.recycle();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		v_height = h;
		v_width = w;
		setUpPaint();
		invalidate();
	}

	private void setUpPaint() {
		mTextPaint = new TextPaint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(mTextColor);
		Typeface tf = TypeFaceProvider.getTypeFace(getContext(), mFontName);
		mTextPaint.setTypeface(tf);
		mTextPaint.setTextSize(mTextSize);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		int width = 0;
		int height = 0;
		if (mSwitchButton != null) {
			width = mSwitchButton.getWidth();
			height = mSwitchButton.getHeight() + getPaddingTop()
					+ getPaddingBottom();
		}

		width = (int) ((width * 2) + (width / 4)) + getPaddingLeft()
				+ getPaddingRight();
		setMeasuredDimension(width, height);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		if (isChecked()) {
			int switchBtnWidth = 0;
			if (mSwitchButton != null) {
				switchBtnWidth = mSwitchButton.getWidth();
			}

			canvas.drawBitmap(mSwitchButton, getWidth() - switchBtnWidth
					- getPaddingRight(), getPaddingTop(), null);

			if (mTextOn != null) {
				Rect textOnRect = getTextBound(mTextOn);
				float x = (getWidth() / 4) - (textOnRect.width() / 2);
				float y = (getHeight() / 2) + (textOnRect.height() / 2);
				canvas.drawText(mTextOn, x, y, mTextPaint);
			}
		} else {
			canvas.drawBitmap(mSwitchButton, getPaddingLeft(), getPaddingTop(),
					null);

			if (mTextOff != null) {
				Rect textOffRect = getTextBound(mTextOff);
				float x = getWidth()
						- ((getWidth() / 4) + (textOffRect.width() / 2));
				float y = (getHeight() / 2) + (textOffRect.height() / 2);
				canvas.drawText(mTextOff, x, y, mTextPaint);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {

		case MotionEvent.ACTION_UP:
			setSwitch();
			break;
		}

		return true;
	}

	private void setSwitch() {
		if (isChecked()) {
			setChecked(false);
		} else {
			setChecked(true);
		}
		invalidate();
	}

	private Rect getTextBound(String text) {
		Rect rect = new Rect();
		mTextPaint.getTextBounds(text, 0, text.length(), rect);
		return rect;
	}
	
	public static class TypeFaceProvider {

		private static final String TYPEFACE_FOLDER = "fonts";

		private static Hashtable<String, Typeface> sTypeFaces = new Hashtable<String, Typeface>(
				10);

		public static Typeface getTypeFace(Context context, String fileName) {

			if (fileName == null) {
				return null;
			}

			Typeface tempTypeface = sTypeFaces.get(fileName);

			if (tempTypeface == null) {
				String fontPath = new StringBuilder(TYPEFACE_FOLDER).append('/')
						.append(fileName).toString();
				tempTypeface = Typeface.createFromAsset(context.getAssets(),
						fontPath);
				sTypeFaces.put(fileName, tempTypeface);
			}

			return tempTypeface;
		}

	}

}
