package com.kpinfotech.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.kpinfotech.global.AppConstant;
import com.kpinfotech.global.AppMethod;
import com.kpinfotech.interfaces.XmlWSAsync;
import com.kpinfotech.model.XmlItem;
import com.kpinfotech.parser.XmlParser;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

public class XmlWS extends AsyncTask<Void, Void, String> implements OnCancelListener {
	
	Activity activity;
	Fragment fragment;
	
	List<NameValuePair> nameValuePair;
	
	String serviceType, wsName;
	Boolean isLoaderEnable = true;
	
	Exception error = null;
	
	XmlWSAsync i_XmlWSAsync;
	
	ArrayList<XmlItem> xmlItems;
	
	public XmlWS(Activity activity, String serviceType, List<NameValuePair> nameValuePair, String wsName, boolean isLoaderEnable) {
		this.i_XmlWSAsync = (XmlWSAsync) activity;
		this.activity = activity;
		this.serviceType = serviceType;
		this.nameValuePair = nameValuePair;
		this.wsName = wsName;
		this.isLoaderEnable = isLoaderEnable;
	}
	
	public XmlWS(Activity activity, Fragment fragment, String serviceType, List<NameValuePair> nameValuePair, String wsName, boolean isLoaderEnable) {
		this.i_XmlWSAsync = (XmlWSAsync) fragment;
		this.activity = activity;
		this.fragment = fragment;
		this.serviceType = serviceType;
		this.nameValuePair = nameValuePair;
		this.wsName = wsName;
		this.isLoaderEnable = isLoaderEnable;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if (isLoaderEnable)
			AppMethod.showProgressDialog(activity, AppConstant.PLEASE_WAIT);
	}
	
	@Override
	public void onCancel(DialogInterface dialog) {
		this.cancel(true);
		AppMethod.dismissProgressDialog(activity);
	}
	
	@Override
	protected String doInBackground(Void... params) {
		
		xmlItems = null;
		try {
			XmlParser parser = new XmlParser();
			xmlItems = parser.parse(getInputStream(wsName));
		} catch (Exception e) {
            this.error = e;
            Log.e("XmlWS =>", e.getMessage());
		}
		
		return null;
	}
	
	public InputStream getInputStream(String link) {
		try {
			URL url = new URL(link);
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w("XML", "Exception while retrieving the input stream", e);
			return null;
		}
	}
	
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		
		if (isLoaderEnable) {
            AppMethod.dismissProgressDialog(activity);
        }
		
		i_XmlWSAsync.onXmlWSResponse(serviceType, xmlItems, error);
	}

}