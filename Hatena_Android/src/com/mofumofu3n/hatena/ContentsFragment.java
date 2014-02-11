package com.mofumofu3n.hatena;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mofumofu3n.hatena.model.Entry;
import com.mofumofu3n.hatena.request.RssParser;
import com.mofumofu3n.hatena.request.XmlArrayRequest;

public class ContentsFragment extends Fragment {
	private static final String TAG = ContentsFragment.class.getSimpleName();
	private RequestQueue mQueue;
	private final XmlArrayRequest mRequest;
	private ListView mListView;
	private ContentsAdapter mAdapter;
	private ArrayList<Entry> mEntryList = new ArrayList<Entry>();

	public ContentsFragment(String url) {

		mRequest = new XmlArrayRequest(url, new Listener<InputStream>() {

			@Override
			public void onResponse(InputStream response) {
				mEntryList.addAll(RssParser.parse(response));
				mAdapter.notifyDataSetChanged();
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

		mQueue = Volley.newRequestQueue(activity);
		mQueue.add(mRequest);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mListView = (ListView) getView().findViewById(R.id.content_list);
		mAdapter = new ContentsAdapter(getActivity(), mEntryList, mQueue);
		mListView.setAdapter(mAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);

		return rootView;
	}

}
