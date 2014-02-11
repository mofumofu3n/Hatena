package com.mofumofu3n.hatena.hot;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.mofumofu3n.hatena.R;
import com.mofumofu3n.hatena.request.XmlArrayRequest;

public class HotFragment extends Fragment {
	private static final String TAG = HotFragment.class.getSimpleName();
	private static final String URL = "http://b.hatena.ne.jp/entrylist/social?sort=hot&threshold=&mode=rss";

	public HotFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		RequestQueue queue = Volley.newRequestQueue(activity);

		XmlArrayRequest request = new XmlArrayRequest(URL,
				new Listener<InputStream>() {

					@Override
					public void onResponse(InputStream response) {
						Log.d(TAG, "GET REQUEST");
						XmlPullParser parser = Xml.newPullParser();
						try {
							parser.setInput(response, "UTF-8");

							int eventType;
							while ((eventType = parser.next()) != XmlPullParser.END_DOCUMENT) {
								// itemタグを見つけたら、addItemsへ
								if (eventType == XmlPullParser.START_TAG
										&& "item".equals(parser.getName())) {
									addItems(parser);
								}
							}
						} catch (XmlPullParserException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.d(TAG, "Error :" + error.getMessage());
					}
				});

		queue.add(request);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Log.d(TAG, "onCreate");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_dummy,
				container, false);


		return rootView;
	}

	/**
	 * DataStoreクラスにタグを格納
	 * 
	 * @param parser
	 * @return
	 */
	private void addItems(XmlPullParser parser) {

		while (true) {
			try {
				int eventType = parser.next();
				if (eventType == XmlPullParser.END_TAG
						&& "item".equals(parser.getName())) {
					break;
				}

				if (eventType == XmlPullParser.START_TAG
						&& "title".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						Log.d(TAG, parser.getText());
					}
				}

				if (eventType == XmlPullParser.START_TAG
						&& "link".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						Log.d(TAG, parser.getText());
					}
				}

				if (eventType == XmlPullParser.START_TAG
						&& "description".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						Log.d(TAG, parser.getText());
					}
				}

				if (eventType == XmlPullParser.START_TAG
						&& "date".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						Log.d(TAG, parser.getText());
					}
				}

				if (eventType == XmlPullParser.START_TAG
						&& "bookmarkcount".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						Log.d(TAG, parser.getText());
					}
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
