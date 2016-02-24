package com.ban.graduationproject;

import android.widget.EditText;
import android.widget.TextView;

public class RechargeFragment extends MyBaseFragment {
	private EditText mInputText;
	private TextView mDataHead1;
	private TextView mDataHead2;
	private TextView mDataHead3;

	public RechargeFragment() {
		super(R.layout.fragment_recording);
	}

	@Override
	protected void initView() {
		mInputText = (EditText) view.findViewById(R.id.input);
		mInputText.setHint(R.string.main_recording_input_hint);
		
		mDataHead1 = (TextView) view.findViewById(R.id.data_head_1);
		mDataHead2 = (TextView) view.findViewById(R.id.data_head_2);
		mDataHead3 = (TextView) view.findViewById(R.id.data_head_3);
	}

	@Override
	protected void initButton() {
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void initListView() {
	}
}
