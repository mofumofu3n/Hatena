package com.mofumofu3n.hatena.slidemenu;

import android.app.ActionBar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SlidingPaneLayout.SimplePanelSlideListener;
import android.view.View;

public class SlideMenuListener extends SimplePanelSlideListener {
	@SuppressWarnings("unused")
	private final FragmentActivity mFragmentActivity;
	private final ActionBar mActionBar;

	public SlideMenuListener(FragmentActivity fragmentActivity) {
		mFragmentActivity = fragmentActivity;
		mActionBar = fragmentActivity.getActionBar();
	}

	@Override
	public void onPanelOpened(View panel) {
		super.onPanelOpened(panel);
		panelOpen();
	}
	
	@Override
	public void onPanelClosed(View panel) {
		super.onPanelClosed(panel);
		panelClosed();
	}
	
	void panelClosed() {
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);
	}
	
	void panelOpen() {
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setHomeButtonEnabled(false);
	}
}
