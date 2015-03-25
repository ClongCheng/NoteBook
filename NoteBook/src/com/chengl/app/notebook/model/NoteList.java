package com.chengl.app.notebook.model;

import java.util.ArrayList;
import java.util.UUID;

import com.chengl.app.notebook.util.LogUtil;
import com.chengl.app.notebook.util.NoteJSONSerializer;

import android.content.Context;

public class NoteList {

	private static NoteList sNoteList;
	private Context mAppcontext;
	private ArrayList<Note> mNotes;
	
	private static final String TAG = "NoteList";
	private static final String FILENAME = "note.json";
	private NoteJSONSerializer mSerializer;
	
	private NoteList(Context appContext) {
		mAppcontext = appContext;
		mSerializer = new NoteJSONSerializer(mAppcontext, FILENAME);
		
		try {
			mNotes = mSerializer.loadNotes();
		} catch (Exception e) {
			mNotes = new ArrayList<Note>();
			LogUtil.d(TAG, "Load Notes failed");
		}
	}
	
	public static NoteList get(Context c) {
		if (sNoteList == null) {
			sNoteList = new NoteList(c.getApplicationContext());
		}
		
		return sNoteList;
	}
	
	public ArrayList<Note> getNotes() {
		
		return mNotes;
	}
	
	public Note getNote(UUID id) {
		for (Note n : mNotes) {
			if (n.getId().equals(id))
				return n;
		}
		
		return null;
	}
	
	public void addNote(Note n) {
		mNotes.add(n);
	}
	
	public void addNote(int index, Note n) {
		mNotes.add(index, n);
	}
	
	public void deleteNote(Note n) {
		mNotes.remove(n);
	}
	
	public boolean saveNotes() {
		try {
			mSerializer.saveNotes(mNotes);
			LogUtil.d(TAG, "Notes saved to file");
			
			return true;
		} catch (Exception e) {
			LogUtil.d(TAG, "Note saved failed");
			return false;
		}
	}
	
}
