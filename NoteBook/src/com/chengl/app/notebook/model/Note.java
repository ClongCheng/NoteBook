package com.chengl.app.notebook.model;

import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {
	
	private UUID mId;
	private String mContent;
	private String mTitle;
	
	private static final String JSON_ID = "id";
	private static final String JSON_TITLE = "title";
	private static final String JSON_CONTENT = "content";
	
	public Note() {
		mId = UUID.randomUUID();
	}
	
	public String getContent() {
		return mContent;
	}
	
	public void setContent(String content) {
		mContent = content;
	}
	
	public String getTitle() {
		return mTitle;
	}
	
	public void setTitle(String title) {
		mTitle = title;
	}
	
	public UUID getId() {
		return mId;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return mTitle;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject json = new JSONObject();
		json.put(JSON_ID, mId.toString());
		json.put(JSON_TITLE, mTitle);
		json.put(JSON_CONTENT, mContent);
		
		return json;
	}
	
	public Note(JSONObject json) throws JSONException {
		mId = UUID.fromString(json.getString(JSON_ID));
		if (json.has(JSON_TITLE)) {
			mTitle = json.getString(JSON_TITLE);
		}
		
		if (json.has(JSON_CONTENT)) {
			mContent = json.getString(JSON_CONTENT);
		}
	}
}
