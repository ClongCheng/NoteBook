package com.chengl.app.notebook.fragment;

import java.util.UUID;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.chengl.app.notebook.R;
import com.chengl.app.notebook.model.Note;
import com.chengl.app.notebook.model.NoteList;
import com.chengl.app.notebook.util.LogUtil;


public class NoteEditFragment extends Fragment {

	private Note mNote;
	private EditText mContentField;
	private EditText mTitle;
	private int fromwhere = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		//getActivity().getActionBar().setDisplayShowHomeEnabled(false);
	
		fromwhere = (Integer)getArguments().getSerializable(NoteReviewFragment.FROM_WHERE);
		if (fromwhere == 2){
			UUID noteId = (UUID)getArguments().getSerializable(NoteReviewFragment.EXTRA_NOTE_ID);
			mNote = NoteList.get(getActivity()).getNote(noteId);
		} else {
			mNote = new Note();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		LogUtil.d("item:", item.toString());
		switch (item.getItemId()) {
		case android.R.id.home:
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				NavUtils.navigateUpFromSameTask(getActivity());
				if (mNote.getTitle() != null){
					LogUtil.d("mNote's title is :", mNote.getTitle());
					if (fromwhere == 1) {
						NoteList.get(getActivity()).addNote(0, mNote);
					}
				}
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.note_edit_layout, container, false);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			if (NavUtils.getParentActivityName(getActivity()) != null) {
				LogUtil.d("onCreateView", "NavUtil is OK!");
				getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
				getActivity().getActionBar().setTitle("±£´æ");
			}
		}
		
		mContentField = (EditText)v.findViewById(R.id.note_edit);
		if (fromwhere == 2) {
			mContentField.setText(mNote.getContent());
		}
		mTitle = (EditText)v.findViewById(R.id.note_title);
		if (fromwhere == 2) {
			mTitle.setText(mNote.getTitle());
		}
		
		mTitle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				mNote.setTitle(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mContentField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				mNote.setContent(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		return v;
	}

	@Override
	public void onPause() {
		super.onPause();
		NoteList.get(getActivity()).saveNotes();
	}
	
	public static Fragment newInstance(UUID noteId, int i) {
		Bundle args = new Bundle();
		if ( i == 1) {
			args.putSerializable(NoteReviewFragment.FROM_WHERE, i);
		} else {
			args.putSerializable(NoteReviewFragment.EXTRA_NOTE_ID, noteId);
			args.putSerializable(NoteReviewFragment.FROM_WHERE, i);
		}
		
		NoteEditFragment fragment = new NoteEditFragment();
		fragment.setArguments(args);
		
		return fragment;
	}
}
