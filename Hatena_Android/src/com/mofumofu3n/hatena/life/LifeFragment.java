package com.mofumofu3n.hatena.life;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mofumofu3n.hatena.R;

public class LifeFragment extends Fragment {
	private ListView mContentView;
	
	public LifeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);
		mContentView = (ListView) rootView.findViewById(R.id.content_list);
		
		return rootView;
	}
}
