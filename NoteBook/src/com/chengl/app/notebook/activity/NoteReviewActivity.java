package com.chengl.app.notebook.activity;

import java.util.UUID;

import android.support.v4.app.Fragment;

import com.chengl.app.notebook.fragment.NoteReviewFragment;

public class NoteReviewActivity extends BaseFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		UUID noteId = (UUID)getIntent().getSerializableExtra(NoteReviewFragment.EXTRA_NOTE_ID);
		
		return NoteReviewFragment.newInstance(noteId);
	}

}
