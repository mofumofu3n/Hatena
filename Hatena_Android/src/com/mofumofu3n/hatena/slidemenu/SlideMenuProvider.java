package com.mofumofu3n.hatena.slidemenu;

import java.util.ArrayList;

public class SlideMenuProvider {
	@SuppressWarnings("unused")
	private static final String TAG = SlideMenuProvider.class.getSimpleName();
	private static final ArrayList<SlideMenuItem> sArrayList = new ArrayList<SlideMenuItem>();
	
	static int getCount() {
		return sArrayList.size();
	}
	
	static SlideMenuItem getItem(int position) {
		return sArrayList.get(position);
	}

	static void load() {
		String[] categories = {
			"人気",
			"総合",
			"世の中",
			"政治と経済",
			"暮らし",
			"学び",
			"テクノロジー",
			"エンタメ",
			"アニメとゲーム",
			"おもしろ",
		};
		
		for (String category : categories) {
			SlideMenuItem item = new SlideMenuItem();
			item.category = category;
			sArrayList.add(item);
		}
	}
}
