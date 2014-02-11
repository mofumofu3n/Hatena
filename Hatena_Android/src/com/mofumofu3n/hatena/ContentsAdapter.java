package com.mofumofu3n.hatena;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.mofumofu3n.hatena.model.Entry;

public class ContentsAdapter extends ArrayAdapter<Entry> {
	private static String TAG = ContentsAdapter.class.getSimpleName();
	// private RequestQueue mQueue;
	private ImageLoader mImageLoader;
	private LayoutInflater mInflater;

	public ContentsAdapter(Context context, ArrayList<Entry> objects, RequestQueue queue) {
		super(context, 0, objects);

		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mImageLoader = new ImageLoader(queue, new LruCacheSample());
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.contents_item, parent,
					false);

			holder = new ViewHolder();
			holder.title = (TextView) convertView.findViewById(R.id.entry_title);
			holder.image = (ImageView) convertView.findViewById(R.id.entry_image);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Entry entry = getItem(position);
		holder.title.setText(entry.title);

		if (entry.image != null) {
			holder.image.setVisibility(View.VISIBLE);
			ImageListener listener = ImageLoader.getImageListener(holder.image,
					R.drawable.no_image /* 表示待ち時の画像 */,
					android.R.drawable.ic_dialog_alert /* エラー時の画像 */);
			mImageLoader.get(entry.image, listener); /* URLから画像を取得する */
		} else {
			holder.image.setVisibility(View.GONE);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView title;
		TextView description;
		ImageView image;
		ImageView favicon;
		TextView date;
		TextView bookmarkCount;
	}
}
