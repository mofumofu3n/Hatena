package com.mofumofu3n.hatena;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.ArrayAdapter;

public class SlideMenuFragment extends ListFragment {
	
	final String[] Data = {
	    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
	    "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
	    "U", "V", "W", "X", "Y", "Z"
	  };	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	    ArrayAdapter<String> adapter =
	    	      new ArrayAdapter<String>(
	    	        getActivity(),
	    	        android.R.layout.simple_list_item_1,
	    	        Data);
	    setListAdapter(adapter);
	}
}
