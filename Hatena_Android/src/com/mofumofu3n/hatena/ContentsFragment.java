package com.mofumofu3n.hatena;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mofumofu3n.hatena.model.Entry;
import com.mofumofu3n.hatena.request.RssParser;
import com.mofumofu3n.hatena.request.XmlArrayRequest;
import com.mofumofu3n.hatena.web.WebActivity;

public class ContentsFragment extends Fragment {
	private static final String TAG = ContentsFragment.class.getSimpleName();
	private RequestQueue mQueue;
	private final XmlArrayRequest mRequest;
	private ContentsAdapter mAdapter;
	private ListView mContentListView;
	private ArrayList<Entry> mEntryList = new ArrayList<Entry>();

	public ContentsFragment(String url) {

		mRequest = new XmlArrayRequest(url, new Listener<InputStream>() {

			@Override
			public void onResponse(InputStream response) {
				mEntryList.addAll(RssParser.parse(response));
				mAdapter.notifyDataSetChanged();
				mProgress.setVisibility(View.GONE);
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
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mAdapter = new ContentsAdapter(getActivity(), mEntryList, mQueue);
		mContentListView = (ListView) getView().findViewById(R.id.content_list);
		mContentListView.setAdapter(mAdapter);
		mContentListView.setOnItemClickListener(mItemClick);

		mProgress = (ProgressBar) getView().findViewById(R.id.empty_view);
		mProgress.setVisibility(View.VISIBLE);

		mQueue.add(mRequest);
	}

	ProgressBar mProgress;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);

		return rootView;
	}
	
	private OnItemClickListener mItemClick = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long arg3) {
			final String url = (mEntryList.get(position)).link;
			
			Intent intent = new Intent(getActivity(), WebActivity.class);
			intent.putExtra("url", url);
			startActivity(intent);

		}
	};

}
