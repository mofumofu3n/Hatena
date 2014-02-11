package com.mofumofu3n.hatena.hot;

import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class ContentsFragment extends Fragment {
	private static final String TAG = ContentsFragment.class.getSimpleName();
	private final XmlArrayRequest mRequest;

	public ContentsFragment(String url) {

		mRequest = new XmlArrayRequest(url, new Listener<InputStream>() {

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

}
