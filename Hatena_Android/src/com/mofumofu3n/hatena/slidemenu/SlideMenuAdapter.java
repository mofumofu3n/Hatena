package com.mofumofu3n.hatena.slidemenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mofumofu3n.hatena.R;

public class SlideMenuAdapter extends ArrayAdapter<SlideMenuItem> {
	@SuppressWarnings("unused")
	private static final String TAG = SlideMenuAdapter.class.getSimpleName();
	private final LayoutInflater mInflater;

	public SlideMenuAdapter(Context context, int resource) {
		super(context, resource);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		SlideMenuProvider.load();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		final ViewHolder holder;
		
		if (view == null) {
			view = mInflater.inflate(R.layout.slide_menu_item, null);
			holder = new ViewHolder();
			holder.titleView = (TextView) view.findViewById(R.id.slide_menu_item_title);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		final SlideMenuItem item = (SlideMenuItem) getItem(position);
		holder.titleView.setText(item.category);
		
		return view;
	}
	
	@Override
	public int getCount() {
		return SlideMenuProvider.getCount();
	}

	@Override
	public SlideMenuItem getItem(int position) {
		return SlideMenuProvider.getItem(position);
	}
	
	class ViewHolder {
		TextView titleView;
	}

}
