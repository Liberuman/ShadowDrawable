package com.sxu.shadowdrawabledemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxu.shadowdrawable.ShadowDrawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*******************************************************************************
 * Description: 
 *
 * Author: Freeman
 *
 * Date: 2019/2/12
 *
 * Copyright: all rights reserved by Freeman.
 *******************************************************************************/
public class RecycleExampleFragment extends Fragment {

	private RecyclerView recyclerView;
	private List<String> dataList = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		recyclerView = new RecyclerView(getActivity());
		return recyclerView;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		for (int i = 0; i  < 20; i++) {
			dataList.add("Item_" + i);
		}

		RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(new RecyclerView.Adapter() {
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
				return new MyViewHolder(new TextView(getActivity()));
			}

			@Override
			public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
				TextView textView = (TextView) holder.itemView;
				int padding = 10 * 3;
				textView.setPadding(padding, padding, padding, padding);
				textView.setGravity(Gravity.CENTER);
				textView.setText(dataList.get(position));
				Random random = new Random();
				textView.setMinimumHeight((int) (120 * 3 + random.nextFloat() * 300));
				ShadowDrawable.setShadowDrawable(textView, Color.parseColor("#2979FF"), 8 * 3,
						Color.parseColor("#ee7C4DFF"), padding, 0, 0);
			}

			@Override
			public int getItemCount() {
				return dataList.size();
			}
		});
		//recyclerView.addItemDecoration(new GridSpacingItemDecoration(2,10,true));
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {

		public MyViewHolder(View itemView) {
			super(itemView);
		}
	}
}
