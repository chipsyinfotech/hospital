package com.mavericks.checkin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.mavericks.checkin.utils.HCFontManager;

public class HCButton extends Button{

	public HCButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		HCFontManager.setFontFromAttributeSet(context, attrs, this);
	}
	

}
