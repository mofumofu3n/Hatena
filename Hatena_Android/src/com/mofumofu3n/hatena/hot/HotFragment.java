package com.mofumofu3n.hatena.hot;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mofumofu3n.hatena.R;
import com.mofumofu3n.hatena.request.RssParser;
import com.mofumofu3n.hatena.request.XmlArrayRequest;

public class HotFragment extends Fragment {
	private static final String TAG = HotFragment.class.getSimpleName();
	private static final String URL = "http://b.hatena.ne.jp/entrylist/social?sort=hot&threshold=&mode=rss";

	public HotFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		RequestQueue queue = Volley.newRequestQueue(activity);
		queue.add(mRequest);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);

		return rootView;
	}

	private final XmlArrayRequest mRequest = new XmlArrayRequest(URL,
			new Listener<InputStream>() {

				@Override
				public void onResponse(InputStream response) {
					RssParser.parse(response);
				}
			}, new ErrorListener() {

				@Override
				public void onErrorResponse(VolleyError error) {
					Log.d(TAG, "Error :" + error.getMessage());
				}
			});

}
