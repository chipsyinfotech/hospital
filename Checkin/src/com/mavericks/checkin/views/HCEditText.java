package com.mavericks.checkin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.mavericks.checkin.utils.HCFontManager;

public class HCEditText extends EditText{

	public HCEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		HCFontManager.setFontFromAttributeSet(context, attrs, this);
	}

}
