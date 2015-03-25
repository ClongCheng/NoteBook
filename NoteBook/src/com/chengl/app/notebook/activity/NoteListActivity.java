package com.chengl.app.notebook.activity;

import com.chengl.app.notebook.fragment.NoteListFragment;

import android.support.v4.app.Fragment;

public class NoteListActivity extends BaseFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		return new NoteListFragment();
	}

}
