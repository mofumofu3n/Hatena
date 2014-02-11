package com.mofumofu3n.hatena.request;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.text.format.Time;
import android.util.Log;
import android.util.TimeFormatException;
import android.util.Xml;

import com.mofumofu3n.hatena.model.Entry;

public class RssParser {
	private static final String TAG = RssParser.class.getSimpleName();

	private RssParser() {
	}

	/**
	 * RSSをパースし、記事のリストを返す
	 * 
	 * @param response
	 * @return 記事{@link Entry}のリスト
	 */
	public static ArrayList<Entry> parse(InputStream response) {
		ArrayList<Entry> entryList = new ArrayList<Entry>();

		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(response, "UTF-8");
			int eventType;
			while ((eventType = parser.next()) != XmlPullParser.END_DOCUMENT) {
				// itemタグを見つけたら、addItemsへ
				if (eventType == XmlPullParser.START_TAG
						&& "item".equals(parser.getName())) {
					entryList.add(parseItem(parser));
				}
			}
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entryList;
	}

	/**
	 * エントリーのプロパティをパース
	 * 
	 * @param parser
	 *            1エントリー
	 * 
	 *            {@link RssParser#parse(InputStream)}
	 * @return
	 */
	private static Entry parseItem(XmlPullParser parser) {
		Entry entry = new Entry();

		while (true) {
			try {
				int eventType = parser.next();
				if (eventType == XmlPullParser.END_TAG
						&& "item".equals(parser.getName())) {
					break;
				}

				// タイトル
				if (eventType == XmlPullParser.START_TAG
						&& "title".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						entry.title = parser.getText();
					}
				}

				// リンク
				if (eventType == XmlPullParser.START_TAG
						&& "link".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						entry.link = parser.getText();
					}
				}

				// 概要
				if (eventType == XmlPullParser.START_TAG
						&& "description".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						entry.description = parser.getText();
					}
				}

				// 記事詳細のHTML
				if (eventType == XmlPullParser.START_TAG
						&& "encoded".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						// HTMLをパースし、記事画像のURLとfaviconを取得する
						final Document doc = Jsoup.parse(parser.getText());
						final Elements els = doc.getElementsByTag("img");

						entry.favicon = (els.get(0)).attr("src");
						if (els.size() == ENABLE_IMAGE_SIZE) {
							entry.image = (els.get(1)).attr("src");
						}
					}
				}

				// 入稿日時
				if (eventType == XmlPullParser.START_TAG
						&& "date".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						entry.date = rfcToLong(parser.getText());
					}
				}

				// はてブ数
				if (eventType == XmlPullParser.START_TAG
						&& "bookmarkcount".equals(parser.getName())) {
					eventType = parser.next();
					if (eventType == XmlPullParser.TEXT) {
						entry.bookmarkCount = Integer.parseInt(parser.getText());
					}
				}
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Log.d(TAG, entry.toString());
		return entry;
	}
	
	/**
	 * 記事画像がある場合のimgタグ数
	 */
	private static final int ENABLE_IMAGE_SIZE = 4;

	/**
	 * RFCフォーマットの日時をLongに変換
	 * @param date YYYY-MM-ddTHH:MM:SS+TIMEZONE
	 * @return long UNIXTIME
	 */
	private static long rfcToLong(String date) {
		Time time = new Time();
		try{
			time.parse3339(date);
		} catch (TimeFormatException e) {
			e.getStackTrace();
			Log.d("","Parse Error" + e);
		} 
		return time.normalize(false);
	}
}
