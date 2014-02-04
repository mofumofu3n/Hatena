package com.mofumofu3n.hatena;

import com.mofumofu3n.hatena.economics.EconomicsFragment;
import com.mofumofu3n.hatena.hot.HotFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity {

	CategoryPagerAdapter mCategoryPagerAdapter;
	ViewPager mViewPager;

	SlidingPaneLayout mSlidingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		mSlidingLayout = (SlidingPaneLayout) findViewById(R.id.pane);
		mSlidingLayout.setPanelSlideListener(new SlideListener(this));
		mSlidingLayout.openPane();

		getSupportFragmentManager().beginTransaction()
				.add(R.id.pane1, new SlideMenuFragment(), "pane1").commit();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.pane2, new HotFragment(), "pane2").commit();

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

}
