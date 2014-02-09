package com.mofumofu3n.hatena.slidemenu;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mofumofu3n.hatena.MainActivity;
import com.mofumofu3n.hatena.fun.FunFragment;

public class SlideMenuFragment extends ListFragment {
	@SuppressWarnings("unused")
	private static final String TAG = SlideMenuFragment.class.getSimpleName();

	private final MainActivity mActivity;
	final String[] Data = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z" };

	public SlideMenuFragment(MainActivity activity) {
		mActivity = activity;
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
		onItemSelected(position);
	}

	private void onItemSelected(int position) {
		final FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager().beginTransaction();
		transaction.replace(mActivity.getMainFragmentId(), new FunFragment(),
				"pane3");
		transaction.commit();
	}
}
