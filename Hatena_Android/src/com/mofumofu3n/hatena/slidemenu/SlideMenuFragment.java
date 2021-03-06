package com.mofumofu3n.hatena.slidemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.mofumofu3n.hatena.Config;
import com.mofumofu3n.hatena.ContentsFragment;
import com.mofumofu3n.hatena.MainActivity;
import com.mofumofu3n.hatena.R;

public class SlideMenuFragment extends ListFragment {
	@SuppressWarnings("unused")
	private static final String TAG = SlideMenuFragment.class.getSimpleName();
	private final MainActivity mActivity;

	public SlideMenuFragment(MainActivity activity) {
		mActivity = activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(new SlideMenuAdapter(getActivity(), R.layout.slide_menu_item));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		onItemSelected(position);
	}

	private void onItemSelected(int position) {
		Fragment fragment = selectedFragment(position);
		
		if (fragment == null) {
			return;
		}
		
		final FragmentTransaction transaction = getActivity()
				.getSupportFragmentManager().beginTransaction();
		transaction.replace(mActivity.getMainFragmentId(), fragment,
				"pane3");
		transaction.commit();

		mActivity.closePane();
	}
	
	private Fragment selectedFragment(int position) {
		switch (position) {
		case 0:
			return new ContentsFragment(Config.RSS_SOCIAL);
		case 1:
			return new ContentsFragment(Config.RSS_SOCIAL);
		case 2:
			return new ContentsFragment(Config.RSS_SOCIAL);
		case 3:
			return new ContentsFragment(Config.RSS_ECONOMICS);
		case 4:
			return new ContentsFragment(Config.RSS_LIFE);
		case 5:
			return new ContentsFragment(Config.RSS_KNOWLEDGE);
		case 6:
			return new ContentsFragment(Config.RSS_IT);
		case 7:
			return new ContentsFragment(Config.RSS_ENTERTAINMENT);
		case 8:
			return new ContentsFragment(Config.RSS_GAME);
		case 9:
			return new ContentsFragment(Config.RSS_FUN);
		}
		return null;
	}
	
}
