package com.mofumofu3n.hatena;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {

	CategoryPagerAdapter mCategoryPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mCategoryPagerAdapter = new CategoryPagerAdapter(
				getApplicationContext(), getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mCategoryPagerAdapter);
		setTab();

	}
	
	PagerTabStrip setTab() {
		PagerTabStrip tab = (PagerTabStrip)findViewById(R.id.pager_title_strip);
		tab.setDrawFullUnderline(true);
		tab.setTabIndicatorColor(Color.GRAY);

		return tab;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
