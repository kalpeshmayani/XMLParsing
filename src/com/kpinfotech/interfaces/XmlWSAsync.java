package com.kpinfotech.interfaces;

import java.util.ArrayList;

import com.kpinfotech.model.XmlItem;

public interface XmlWSAsync {
	void onXmlWSResponse(String serviceType, ArrayList<XmlItem> xmlItems, Exception error);
}