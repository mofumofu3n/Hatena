package com.mofumofu3n.hatena;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mofumofu3n.hatena.economics.EconomicsFragment;
import com.mofumofu3n.hatena.entertainment.EntertainmentFragment;
import com.mofumofu3n.hatena.fun.FunFragment;
import com.mofumofu3n.hatena.game.GameFragment;
import com.mofumofu3n.hatena.hot.ContentsFragment;
import com.mofumofu3n.hatena.it.ItFragment;
import com.mofumofu3n.hatena.knowledge.KnowledgeFragment;
import com.mofumofu3n.hatena.life.LifeFragment;
import com.mofumofu3n.hatena.popular.PopularFragment;
import com.mofumofu3n.hatena.social.SocialFragment;

public class CategoryPagerAdapter extends FragmentPagerAdapter {
	private final Context mContext;

	public CategoryPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
//		case 0:
//			return new PopularFragment();
//		case 1:
//			return new ContentsFragment();
//		case 2:
//			return new SocialFragment();
//		case 3:
//			return new EconomicsFragment();
//		case 4:
//			return new LifeFragment();
//		case 5:
//			return new KnowledgeFragment();
//		case 6:
//			return new ItFragment();
//		case 7:
//			return new EntertainmentFragment();
//		case 8:
//			return new GameFragment();
//		case 9:
//			return new FunFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 10;
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return mContext.getString(R.string.title_popular);
		case 1:
			return mContext.getString(R.string.title_hot);
		case 2:
			return mContext.getString(R.string.title_social);
		case 3:
			return mContext.getString(R.string.title_economics);
		case 4:
			return mContext.getString(R.string.title_life);
		case 5:
			return mContext.getString(R.string.title_knowledge);
		case 6:
			return mContext.getString(R.string.title_it);
		case 7:
			return mContext.getString(R.string.title_entertainment);
		case 8:
			return mContext.getString(R.string.title_game);
		case 9:
			return mContext.getString(R.string.title_fun);
		}
		return null;
	}

}
