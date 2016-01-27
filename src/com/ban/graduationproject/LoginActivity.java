package com.ban.graduationproject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 登陆Activity
 * 
 * @author Ban
 * 
 */
public class LoginActivity extends MyBaseActivity {
	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;
	private String mPassword;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginFormMainView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;

	private CheckBox mCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState, R.layout.activity_login);

		int messageID = this.getIntent().getIntExtra("warning", 0);
		if (messageID != 0) {// 有信息就提示
			showWarningMessage(getString(messageID));
		}
	}

	@Override
	public void initView() {
		// Set up the login form.
		mEmailView = (EditText) findViewById(R.id.email);

		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});

		mLoginFormView = findViewById(R.id.login_form);
		mLoginFormMainView = findViewById(R.id.login_form_main);

		// 取屏高
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		// android.view.ViewGroup.LayoutParams params = mLoginFormMainView
		// .getLayoutParams();
		// params.height = displayMetrics.heightPixels;
		// mLoginFormMainView.setLayoutParams(params);

		// mLoginFormMainView.getLayoutParams().height =
		// displayMetrics.heightPixels;

		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		mCheckBox = (CheckBox) findViewById(R.id.login_remerber);
		mCheckBox.setChecked(true);
		// mCheckBox.setChecked(MySampleDate.getBooleanValue("remerber"));
	}

	@Override
	public void initButton() {
		View.OnClickListener clickListener = new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				int id = view.getId();
				if (id == R.id.sign_in_button) {
					attemptLogin();
				}
			}
		};

		findViewById(R.id.sign_in_button).setOnClickListener(clickListener);
	}

	@Override
	public void initListView() {
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		mLoginFormMainView.invalidate();
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// 检查用户名
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		}
		// else if (!mEmail.contains("@")) {
		// mEmailView.setError(getString(R.string.error_invalid_email));
		// focusView = mEmailView;
		// cancel = true;
		// }
		if (cancel) {
			focusView.requestFocus();
			return;
		}

		// 检查密码
		if (TextUtils.isEmpty(mPassword)) {
			mPasswordView.setError(getString(R.string.error_field_required));
			focusView = mPasswordView;
			cancel = true;
		} 
		if (cancel) {
			focusView.requestFocus();
			return;
		}

		// Show a progress spinner, and kick off a background task to
		// perform the user login attempt.
		mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
		showProgress(true);
		mAuthTask = new UserLoginTask();
		mAuthTask.execute((Void) null);
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserLoginTask extends AsyncTask<Void, Void, Integer> {
		@Override
		protected Integer doInBackground(Void... params) {
			int retCode = 0;

			if (mEmail.equals("root") && mPassword.equals("root")) {
				retCode = 1;
			}

			return retCode;
		}

		@Override
		protected void onPostExecute(final Integer resultCode) {
			mAuthTask = null;

			if (resultCode == 1) {
				Log.v("Ban", "login success");
				doLoginSuccess();
				return;
			} else {
				showProgress(false);
				// 用户名或者密码错误
				mPasswordView
						.setError(getString(R.string.error_incorrect_password));
				mPasswordView.requestFocus();
			}
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}
	}

	/**
	 * 登陆成功后的操作
	 */
	private void doLoginSuccess() {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);

		finish();
	}
}
