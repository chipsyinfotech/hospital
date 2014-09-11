package com.mavericks.checkin.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.mavericks.checkin.utils.HCFontManager;

public class HCRadioButton extends RadioButton{

	public HCRadioButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		HCFontManager.setFontFromAttributeSet(context, attrs, this);
	}

}
