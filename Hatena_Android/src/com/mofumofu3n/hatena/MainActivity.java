package com.mofumofu3n.hatena;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.mofumofu3n.hatena.hot.ContentsFragment;
import com.mofumofu3n.hatena.slidemenu.SlideMenuFragment;
import com.mofumofu3n.hatena.slidemenu.SlideMenuListener;

public class MainActivity extends FragmentActivity {
	CategoryPagerAdapter mCategoryPagerAdapter;
	ViewPager mViewPager;

	SlidingPaneLayout mSlidingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.pane);
		mSlidingLayout.setPanelSlideListener(new SlideMenuListener(this));
		mSlidingLayout.openPane();

		getSupportFragmentManager().beginTransaction()
				.add(getSlidingMenuId(), new SlideMenuFragment(this), "pane1").commit();
		getSupportFragmentManager().beginTransaction()
				.add(getMainFragmentId(), new ContentsFragment(Config.RSS_GAME), "pane2").commit();

		// setContentView(R.layout.activity_main);
		//
		// mCategoryPagerAdapter = new CategoryPagerAdapter(
		// getApplicationContext(), getSupportFragmentManager());
		//
		// mViewPager = (ViewPager) findViewById(R.id.pager);
		// mViewPager.setAdapter(mCategoryPagerAdapter);
		// setTab();

	}

	PagerTabStrip setTab() {
		PagerTabStrip tab = (PagerTabStrip) findViewById(R.id.pager_title_strip);
		tab.setDrawFullUnderline(true);
		tab.setTabIndicatorColor(Color.GRAY);

		return tab;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Optionを押した時のSlidingPanel出し分け
		if (mSlidingLayout.isOpen()) {
			mSlidingLayout.closePane();
		} else {
			mSlidingLayout.openPane();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int getSlidingMenuId() {
		return R.id.pane1;
	}

	public int getMainFragmentId() {
		return R.id.pane2;
	}
	
}
