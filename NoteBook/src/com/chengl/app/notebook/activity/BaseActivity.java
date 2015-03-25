package com.chengl.app.notebook.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.chengl.app.notebook.util.ActivityCollector;
import com.chengl.app.notebook.util.LogUtil;

public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LogUtil.d("BaseActivity", getClass().getSimpleName());
		ActivityCollector.addActivity(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
}