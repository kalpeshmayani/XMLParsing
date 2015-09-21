package com.kpinfotech.webservice;

import com.kpinfotech.global.WSConstant;

import android.app.Activity;

public class WebServices {

	// XML
	
	public void getItemList(Activity activity, Boolean isLoaderEnable) {
		new XmlWS(activity, WSConstant.XML_RT_GET_ITEM_LIST, null, WSConstant.XML_WS_GET_ITEM_LIST, isLoaderEnable).execute();
	}
	
}