package com.sxu.shadowdrawabledemo;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AndroidException;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class PagerExampleFragment extends Fragment {

	private ViewPager viewPager;
	private List<String> dataList = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		viewPager = new ViewPager(getActivity());
		viewPager.setClipChildren(false);
		viewPager.setClipToPadding(false);
		int padding = 12 * 3;
		viewPager.setPadding(padding, 0, padding, 0);
		viewPager.setPageMargin(-padding/2);
		return viewPager;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		for (int i = 0; i  < 5; i++) {
			dataList.add("Item_" + i);
		}

		viewPager.setAdapter(new PagerAdapter() {
			@Override
			public int getCount() {
				return dataList.size();
			}

			@Override
			public boolean isViewFromObject(View view, Object object) {
				return view == object;
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				TextView textView = new TextView(getActivity());
				int padding = 12 * 3;
				textView.setPadding(padding, padding, padding, padding);
				textView.setGravity(Gravity.CENTER);
				textView.setText(dataList.get(position));
				textView.setMinimumHeight(240*3);
				ShadowDrawable.setShadowDrawable(textView, Color.parseColor("#2979FF"), 8 * 3,
						Color.parseColor("#fe2979FF"), padding, 0, 0);
				container.addView(textView);
				return textView;
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				container.removeView((View) object);
			}
		});
	}
}
