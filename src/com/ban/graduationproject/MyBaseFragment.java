package com.ban.graduationproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MyBaseFragment extends Fragment {
	protected View view;
	private int layoutResource;

	public MyBaseFragment(int layoutResource) {
		this.layoutResource = layoutResource;
	}

//	@Override
//	public void onAttach(Activity activity) {
//		super.onAttach(activity);
//		this.activity = activity;
//	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(layoutResource, container, false);

		initView();
		initButton();
		initData();
		initListView();

		return view;
	}
	abstract protected void initView();

	abstract protected void initButton();

	abstract protected void initData();

	abstract protected void initListView();


}
