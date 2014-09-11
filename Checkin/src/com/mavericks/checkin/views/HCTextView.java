package com.mavericks.checkin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mavericks.checkin.utils.HCFontManager;

public class HCTextView extends TextView{

	public HCTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		HCFontManager.setFontFromAttributeSet(context, attrs, this);
		
	}
	

}
