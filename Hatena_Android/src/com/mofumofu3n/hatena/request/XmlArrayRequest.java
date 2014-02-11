package com.mofumofu3n.hatena.request;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class XmlArrayRequest extends Request<InputStream> {
	private Listener<InputStream> mListener;
	
	public XmlArrayRequest(String url, Listener<InputStream> listener, ErrorListener errorListener) {
		this(Method.GET, url, listener, errorListener);
	}

	public XmlArrayRequest(int method, String url, Listener<InputStream> listener, ErrorListener errorListener) {
		super(method, url, errorListener);
		mListener = listener;
	}

	@Override
	protected Response<InputStream> parseNetworkResponse(
			NetworkResponse response) {
		InputStream is = new ByteArrayInputStream(response.data);  
        return Response.success(is, HttpHeaderParser.parseCacheHeaders(response));	
	}

	@Override
	protected void deliverResponse(InputStream response) {
		mListener.onResponse(response);
	}

}
