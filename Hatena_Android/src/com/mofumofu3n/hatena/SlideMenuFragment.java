package com.mofumofu3n.hatena;

import com.mofumofu3n.hatena.fun.FunFragment;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SlideMenuFragment extends ListFragment {
	private final FragmentActivity mFragmentActivity;

	final String[] Data = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };
	
	public SlideMenuFragment(FragmentActivity activity) {
		mFragmentActivity = activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, Data);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		// TODO 何かおかしい
		final FragmentTransaction transaction = mFragmentActivity.getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.pane2, new FunFragment(), "pane3");
		transaction.commit();
	}
}
