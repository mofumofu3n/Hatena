package com.mofumofu3n.hatena.model;

public class Entry {
	public String title;
	public String link;
	public String description;
	public String image;
	public String favicon;
	public long date;
	public int bookmarkCount;

	@Override
	public String toString() {
		return String.format("Title: %s\nLink: %s\nDescription: %s\nImage: \nFavicon: %s\n", title,
				link, description, image, favicon);
	}
}
