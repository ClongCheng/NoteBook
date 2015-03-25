package com.chengl.app.notebook.activity;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.chengl.app.notebook.R;
import com.chengl.app.notebook.fragment.NoteReviewFragment;
import com.chengl.app.notebook.model.Note;
import com.chengl.app.notebook.model.NoteList;

public class NotePagerActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private ArrayList<Note> mNotes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_review_pager);
		
		mViewPager = (ViewPager)findViewById(R.id.viewPager);
		
		mNotes = NoteList.get(this).getNotes();
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mNotes.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				Note note = mNotes.get(arg0);
				return NoteReviewFragment.newInstance(note.getId());
			}
		});
		
		UUID noteId = (UUID)getIntent().getSerializableExtra(NoteReviewFragment.EXTRA_NOTE_ID);
		for (int i = 0; i < mNotes.size(); i++) {
			if (mNotes.get(i).getId().equals(noteId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
	}
}
