package com.chengl.app.notebook.fragment;

import java.util.ArrayList;
import android.support.v4.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.chengl.app.notebook.R;
import com.chengl.app.notebook.activity.NoteEditActivity;
import com.chengl.app.notebook.activity.NotePagerActivity;
import com.chengl.app.notebook.model.Note;
import com.chengl.app.notebook.model.NoteList;
import com.chengl.app.notebook.util.ActivityCollector;
import com.chengl.app.notebook.util.LogUtil;
import com.chengl.app.notebook.util.UploadUtil;

public class NoteListFragment extends ListFragment {

	private ArrayList<Note> mNotes;
	private static final String TAG = "NoteListFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		//getActivity().getActionBar().setDisplayShowHomeEnabled(false);
		
		mNotes = NoteList.get(getActivity()).getNotes();
		ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(getActivity(), R.layout.note_list_view, mNotes);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		Note n = (Note)(getListAdapter()).getItem(position);
		LogUtil.d(TAG, n.getTitle() + "was clicked");
		
		Intent i = new Intent(getActivity(), NotePagerActivity.class);
		i.putExtra(NoteReviewFragment.EXTRA_NOTE_ID, n.getId());
		startActivity(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((ArrayAdapter<Note>)getListAdapter()).notifyDataSetChanged();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.note_list_menu, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.add_new_item:
			/*Note note = new Note();
			NoteList.get(getActivity()).addNote(note);*/
			Intent i = new Intent(getActivity(), NoteEditActivity.class);
			i.putExtra(NoteReviewFragment.FROM_WHERE, 1);
			startActivity(i);
			return true;
		case R.id.finish:
			ActivityCollector.finishAll();
			return true;
			
		case R.id.upload:			
			UploadUtil.uploaddata();
			Toast.makeText(getActivity(), "Upload Success!", Toast.LENGTH_SHORT).show();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = super.onCreateView(inflater, container, savedInstanceState);
		ListView listView = (ListView)v.findViewById(android.R.id.list);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
			
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.note_list_item_context, menu);

				return true;
			}
			
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.menu_list_delete_note:
					@SuppressWarnings("unchecked")
					ArrayAdapter<Note> adapter = (ArrayAdapter<Note>)getListAdapter();
					NoteList noteList = NoteList.get(getActivity());
					for (int i = adapter.getCount() - 1; i >= 0; i--) {
						if (getListView().isItemChecked(i)) {
							noteList.deleteNote(adapter.getItem(i));
						}
					}
					mode.finish();
					adapter.notifyDataSetChanged();
					
					return true;

				default:
					return false;
				}
			}
			
			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				
			}
		});
		
		return v;
	}
}
