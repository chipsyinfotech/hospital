package com.mavericks.checkin.holders;

import java.io.Serializable;

import org.apache.http.message.BasicNameValuePair;

public class HCNameValuePair extends BasicNameValuePair implements Serializable{
	public HCNameValuePair(String name, String value) {
        super(name, value);
    }
}
