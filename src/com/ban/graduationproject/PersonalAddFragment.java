package com.ban.graduationproject;

import java.util.Calendar;
import java.util.Date;

import com.ban.graduationproject.database.BaseDAO;
import com.ban.graduationproject.database.CarDAO;
import com.ban.graduationproject.database.UserDAO;
import com.ban.graduationproject.entity.Car;
import com.ban.graduationproject.entity.User;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PersonalAddFragment extends MyBaseFragment {
	private EditText mNameInput;// 姓名
	private EditText mTelInput;// 电话
	private EditText mCarNumberInput;// 车牌号
	private RadioGroup mSexRadioGroup;// 性别
	private RadioGroup mCarTypeGroup;// 车类型
	private DatePicker mDeadlineDatePicker;// 到期时间
	private Button mOkButton;// 确定按钮
	private Button mCancleButton;// 取消按钮

	private MainActivity parentActivity;

	public PersonalAddFragment() {
		super(R.layout.fragment_personal_add);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		parentActivity = (MainActivity) activity;
	}

	@Override
	protected void initView() {
		mNameInput = (EditText) view.findViewById(R.id.add_name);
		mTelInput = (EditText) view.findViewById(R.id.add_tel);
		mCarNumberInput = (EditText) view.findViewById(R.id.add_car_number);
		mSexRadioGroup = (RadioGroup) view.findViewById(R.id.add_sex);
		mCarTypeGroup = (RadioGroup) view.findViewById(R.id.add_car_type);
		mDeadlineDatePicker = (DatePicker) view.findViewById(R.id.add_deadline);
		mOkButton = (Button) view.findViewById(R.id.add_ok_btn);
		mCancleButton = (Button) view.findViewById(R.id.add_cancle_btn);

		((RadioButton) mSexRadioGroup.getChildAt(0)).setChecked(true);
		((RadioButton) mCarTypeGroup.getChildAt(0)).setChecked(true);
	}

	@Override
	protected void initButton() {
		OnClickListener clickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (v == mOkButton) {
					addUserAndCar();
				} else if (v == mCancleButton) {
					parentActivity.goToPersonal();
				}
			}
		};

		mOkButton.setOnClickListener(clickListener);
		mCancleButton.setOnClickListener(clickListener);
	}

	@Override
	protected void initData() {
	}

	@Override
	protected void initListView() {
	}

	private void addUserAndCar() {
		if (checkInfo()) {
			parentActivity.showProgressDialog(
					getString(R.string.sys_info_adding), false);
			User user = new User();
			// 姓名
			user.setName(mNameInput.getText().toString());
			// 性别
			if (mSexRadioGroup.getCheckedRadioButtonId() == R.id.add_sex_female)
				user.setSex(false);
			else
				user.setSex(true);
			// 电话
			user.setTel(Long.parseLong(mTelInput.getText().toString()));
			// 创建时间
			user.setCreated(new Date());
			// 到期时间
			Calendar calendar = Calendar.getInstance();
			calendar.set(mDeadlineDatePicker.getYear(),
					mDeadlineDatePicker.getMonth() + 1,
					mDeadlineDatePicker.getDayOfMonth());
			user.setDeadline(calendar.getTime());
			// 用户状态
			user.setStation(1);
			// =========================

			Car car = new Car();
			// 车辆类型
			car.setType(0);// 默认电单车
			int carTypeId = R.id.add_car_type_0;
			for (int i = 0; i < 3; i++) {
				if (carTypeId == mCarTypeGroup.getCheckedRadioButtonId()) {
					car.setType(i);
					break;
				}
				carTypeId++;
			}
			// 车牌号
			car.setCarNumber(mCarNumberInput.getText().toString());

			UserDAO userDAO = new UserDAO(parentActivity);
			CarDAO carDAO = new CarDAO(parentActivity);
			boolean f = true;
			BaseDAO.database.beginTransaction();
			try {
				f &= carDAO.insertNew(car);
				if (f) {//如果车辆加成功了
					car = carDAO.findByExample(car);
					user.setCarId(car.getId());
				}
				f &= userDAO.insertNew(user);

				parentActivity.dismissProgressDialog();
				if (f) {
					parentActivity
							.showWarningMessage(getString(R.string.sys_info_adding_suc));
					BaseDAO.database.setTransactionSuccessful();
				} else {
					parentActivity
							.showWarningMessage(getString(R.string.sys_info_adding_fail));
				}
			} finally {
				BaseDAO.database.endTransaction();
			}
			
		}
	}

	private boolean checkInfo() {
		if (TextUtils.isEmpty(mNameInput.getText())) {
			mNameInput.setError(getString(R.string.error_field_required));
			return false;
		}
		if (TextUtils.isEmpty(mTelInput.getText())) {
			mTelInput.setError(getString(R.string.error_field_required));
			return false;
		}
		if (TextUtils.isEmpty(mCarNumberInput.getText())) {
			mCarNumberInput.setError(getString(R.string.error_field_required));
			return false;
		}

		return true;
	}

}
