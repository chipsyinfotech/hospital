
/*! * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * @File:
 *		HUMMainMenuFragment.java
 * @Project:
 *		Hummer
 * @Abstract:
 *		
 * @Copyright:
 *     		Copyright © 2012-2013, Viacom 18 Media Pvt. Ltd 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/*! Revision history (Most recent first)
 Created by vijayalaxmi on 06-Aug-2013
 */

package com.mavericks.checkin.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mavericks.checkin.R;

/**
 * @author vijayalaxmi
 *
 */
public class HCMainMenuFrag extends Fragment {

	String[] menuList;
	OnMenuItemSelectedListener mCallback;
	static int mSelectedItem = 0;
//	ABMainMenuAdapter menuAdapter;

	public interface OnMenuItemSelectedListener {
		public void onMenuItemSelected(int position);
	}

	public HCMainMenuFrag() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.frag_menu_items, null);
//		menuList = getActivity().getResources().getStringArray(R.array.home_menu_list);
//		menuAdapter = new ABMainMenuAdapter(getActivity(), 
//				R.layout.listitem_menu, menuList);
//		setListAdapter(menuAdapter);
//		menuAdapter.setSelectedPos(mSelectedItem);
//		menuAdapter.notifyDataSetChanged();
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {		
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {	
		super.onAttach(activity);
		mCallback = (OnMenuItemSelectedListener) activity;
		
	}

//	@Override
//	public void onListItemClick(ListView l, View v, int position, long id) {
//		
//		if (mSelectedItem != position) {
//			mSelectedItem = position;
//			mCallback.onMenuItemSelected(position);
//			menuAdapter.notifyDataSetChanged();	
//		}
//	}


	public void setMenuSelection(int position) {
		mSelectedItem = position;
//		getListView().setItemChecked(position, true);
//		getListView().setSelection(position);
//		menuAdapter.setSelectedPos(position);
//		menuAdapter.notifyDataSetChanged();
	}
	
	public int getSelectedMenuItem() {
		return mSelectedItem;
	}
}
