package com.ban.graduationproject;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonalFragment extends MyBaseFragment {
	private EditText mInputText;
	private ImageView mAddimageView;
	private MainActivity parentActivity;

	public PersonalFragment() {
		super(R.layout.fragment_personal);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		parentActivity = (MainActivity) activity;
	}

	@Override
	protected void initView() {
		mInputText = (EditText) view.findViewById(R.id.input);
		mInputText.setHint(R.string.main_recording_input_hint);

		mAddimageView = (ImageView) view.findViewById(R.id.add);
	}

	@Override
	protected void initButton() {
		OnClickListener clickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == mAddimageView) {
					addUserAndCar();
				}
			}
		};

		mAddimageView.setOnClickListener(clickListener);
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void initListView() {
	}

	private void addUserAndCar() {
		parentActivity.goToPersonalAdd();
	}
}
