package com.chengl.app.notebook.util;

public interface HttpCallbackListener {
	
	void getFinish (String response);
	
	void postFinish ();
	
	void onError();
}
