package com.chengl.app.notebook.fragment;

import java.util.UUID;

import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chengl.app.notebook.R;
import com.chengl.app.notebook.activity.NoteEditActivity;
import com.chengl.app.notebook.model.Note;
import com.chengl.app.notebook.model.NoteList;

public class NoteReviewFragment extends Fragment {

	private Note mNote;
	private TextView mContent;
	private TextView mTitle;
	public static final String EXTRA_NOTE_ID = "com.chengl.app.notebook.note_id";
	public static final String FROM_WHERE = "com.chengl.app.notebook.from_where";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		//getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		UUID noteId = (UUID)getArguments().getSerializable(EXTRA_NOTE_ID);
		
		mNote = NoteList.get(getActivity()).getNote(noteId);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.note_review_layout, container, false);
		
		mContent = (TextView)v.findViewById(R.id.note_content_review);
		mContent.setMovementMethod(ScrollingMovementMethod.getInstance());
		mTitle = (TextView)v.findViewById(R.id.note_title_review);
		
		mTitle.setText(mNote.getTitle());
		mContent.setText(mNote.getContent());
		
		return v;
	}
	
	public static Fragment newInstance(UUID noteId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_NOTE_ID, noteId);
		
		NoteReviewFragment fragment = new NoteReviewFragment();
		fragment.setArguments(args);
		
		return fragment;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.note_review_menu_edit, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.edit_item:
			Intent i = new Intent(getActivity(), NoteEditActivity.class);
			i.putExtra(EXTRA_NOTE_ID, mNote.getId());
			i.putExtra(FROM_WHERE, 2);
			startActivity(i);

			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	
}
