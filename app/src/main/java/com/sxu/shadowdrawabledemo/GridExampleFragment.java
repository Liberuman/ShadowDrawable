package com.sxu.shadowdrawabledemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.sxu.shadowdrawable.ShadowDrawable;

import java.util.ArrayList;
import java.util.List;

/*******************************************************************************
 * Description: 
 *
 * Author: Freeman
 *
 * Date: 2019/2/12
 *
 * Copyright: all rights reserved by Freeman.
 *******************************************************************************/
public class GridExampleFragment extends Fragment {

	private GridView gridView;
	private List<String> dataList = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		gridView = new GridView(getActivity());
		gridView.setClipToPadding(false);
		int padding = 18 * 3;
		gridView.setPadding(padding, padding, padding, padding);
		return gridView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		for (int i = 0; i  < 20; i++) {
			dataList.add("Item_" + i);
		}

		gridView.setHorizontalSpacing(0);
		gridView.setVerticalSpacing(0);
		gridView.setNumColumns(3);
		gridView.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return dataList.size();
			}

			@Override
			public String getItem(int position) {
				return dataList.get(position);
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				TextView textView = new TextView(getActivity());
				int padding = 6 * 3;
				textView.setPadding(padding, padding, padding, padding);
				textView.setGravity(Gravity.CENTER);
				textView.setText(getItem(position));
				textView.setMinimumHeight(120*3);
				ShadowDrawable.setShadowDrawable(textView, Color.parseColor("#2979FF"), 8 * 3,
						Color.parseColor("#ee7C4DFF"), padding, 0, 0);
				return textView;
			}
		});
	}
}
