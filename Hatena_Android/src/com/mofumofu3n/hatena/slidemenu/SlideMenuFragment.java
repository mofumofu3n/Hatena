package com.mofumofu3n.hatena.slidemenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.mofumofu3n.hatena.MainActivity;
import com.mofumofu3n.hatena.R;
import com.mofumofu3n.hatena.economics.EconomicsFragment;
import com.mofumofu3n.hatena.entertainment.EntertainmentFragment;
import com.mofumofu3n.hatena.fun.FunFragment;
import com.mofumofu3n.hatena.game.GameFragment;
import com.mofumofu3n.hatena.hot.HotFragment;
import com.mofumofu3n.hatena.it.ItFragment;
import com.mofumofu3n.hatena.knowledge.KnowledgeFragment;
import com.mofumofu3n.hatena.life.LifeFragment;
import com.mofumofu3n.hatena.popular.PopularFragment;
import com.mofumofu3n.hatena.social.SocialFragment;

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
	}
	
	private Fragment selectedFragment(int position) {
		switch (position) {
		case 0:
			return new HotFragment();
		case 1:
			return new PopularFragment();
		case 2:
			return new SocialFragment();
		case 3:
			return new EconomicsFragment();
		case 4:
			return new LifeFragment();
		case 5:
			return new KnowledgeFragment();
		case 6:
			return new ItFragment();
		case 7:
			return new EntertainmentFragment();
		case 8:
			return new GameFragment();
		case 9:
			return new FunFragment();
		}
		return null;
	}
	
}
