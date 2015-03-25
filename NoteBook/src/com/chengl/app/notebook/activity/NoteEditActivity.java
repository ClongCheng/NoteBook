package com.chengl.app.notebook.activity;

import java.util.UUID;
import android.support.v4.app.Fragment;

import com.chengl.app.notebook.fragment.NoteEditFragment;
import com.chengl.app.notebook.fragment.NoteReviewFragment;

public class NoteEditActivity extends BaseFragmentActivity {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		UUID noteId = (UUID)getIntent().getSerializableExtra(NoteReviewFragment.EXTRA_NOTE_ID);
		int i = (Integer)getIntent().getSerializableExtra(NoteReviewFragment.FROM_WHERE);
		
		return NoteEditFragment.newInstance(noteId, i);
	}

}
