package com.sxu.shadowdrawabledemo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sxu.shadowdrawable.ShadowDrawable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		TextView textView1 = findViewById(R.id.text1);
		TextView textView2 = findViewById(R.id.text2);
		TextView textView3 = findViewById(R.id.text3);
		TextView textView4 = findViewById(R.id.text4);
		TextView listExampleText = findViewById(R.id.list_example_text);
		TextView gridExampleText = findViewById(R.id.grid_example_text);
		TextView recycleExampleText = findViewById(R.id.recycle_example_text);
		TextView pagerExampleText = findViewById(R.id.view_page_example_text);

		ShadowDrawable.setShadowDrawable(textView1, Color.parseColor("#2979FF"), dpToPx(8),
				Color.parseColor("#992979FF"), dpToPx(6), 0, 0);
		ShadowDrawable.setShadowDrawable(textView2, new int[] {
				Color.parseColor("#536DFE"), Color.parseColor("#7C4DFF")}, dpToPx(8),
				Color.parseColor("#997C4DFF"), dpToPx(6), dpToPx(3), dpToPx(3));

		ShadowDrawable.setShadowDrawable(textView3, ShadowDrawable.SHAPE_CIRCLE, Color.parseColor("#2979FF"),
				0, Color.parseColor("#aa536DFE"), dpToPx(10), 0, 0);
		ShadowDrawable.setShadowDrawable(textView4, ShadowDrawable.SHAPE_CIRCLE, Color.parseColor("#7C4DFF"),
				dpToPx(8), Color.parseColor("#992979FF"), dpToPx(6), dpToPx(3), dpToPx(3));

		listExampleText.setOnClickListener(this);
		gridExampleText.setOnClickListener(this);
		recycleExampleText.setOnClickListener(this);
		pagerExampleText.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int containerType = -1;
		switch (v.getId()) {
			case R.id.list_example_text:
				containerType = ExampleContainerActivity.CONTAINER_TYPE_LIST;
				break;
			case R.id.grid_example_text:
				containerType = ExampleContainerActivity.CONTAINER_TYPE_GRID;
				break;
			case R.id.recycle_example_text:
				containerType = ExampleContainerActivity.CONTAINER_TYPE_RECYCLE;
				break;
			case R.id.view_page_example_text:
				containerType = ExampleContainerActivity.CONTAINER_TYPE_PAGER;
				break;
			default:
				break;
		}
		if (containerType != -1) {
			ExampleContainerActivity.enter(this, containerType);
		}
	}

	private int dpToPx(int dp) {
		return (int) (Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
	}
}
