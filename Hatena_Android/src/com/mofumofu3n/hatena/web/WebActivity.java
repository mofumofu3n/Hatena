package com.mofumofu3n.hatena.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.mofumofu3n.hatena.R;

public class WebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		WebView webView = (WebView) findViewById(R.id.webview);
		webView.loadUrl(url);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
}
