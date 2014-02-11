package com.mofumofu3n.hatena.request;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class RssParser {
	private static final String TAG = RssParser.class.getSimpleName();

	private RssParser() {
	}

	public static void parse(InputStream response) {
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

	/**
	 * DataStoreクラスにタグを格納
	 * 
	 * @param parser
	 * @return
	 */
	private static void addItems(XmlPullParser parser) {

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
				
				if (eventType == XmlPullParser.START_TAG && "encoded".equals(parser.getName())) {
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
