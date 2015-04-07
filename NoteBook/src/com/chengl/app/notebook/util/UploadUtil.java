package com.chengl.app.notebook.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import android.content.Context;
import android.os.AsyncTask;
import android.text.GetChars;
import android.util.Log;
import android.widget.Toast;

public class UploadUtil {
	private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 10 * 1000;
	private static final String CHARSET = "utf-8";	
	
	public static void uploaddata() {
    	
    	new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... params) {
				UploadUtil upload = new UploadUtil();
				String result = upload.uploadFile("/data/data/com.chengl.app.notebook/files/note.json", "http://10.0.2.2:8080/test/DoTest");

				return result;
			}

			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				Log.d("result", result);
			}
    		
    		
		}.execute();
    	
    }


	public static String uploadFile(String filePath, String requestURL) {
		
		String result = "";
		File file = new File(filePath);
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(TIME_OUT);
			connection.setReadTimeout(TIME_OUT);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setUseCaches(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Charset", CHARSET);
			connection.setRequestProperty("connection", "keep-alive");
			Log.d("test", "1");
			
			if (filePath != null) {
				Log.d("test", "2");
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				StringBuffer sb = new StringBuffer();
				Log.d("test", "3");
				Log.d("test", "4");
				Log.d("test", "5");
				Log.d("test", "6");
				InputStream is = new FileInputStream(filePath);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				Log.d("test", "7");
				is.close();
				dos.flush();
				Log.d("test", "8");
				int res = connection.getResponseCode();
				Log.e(TAG, "response code:" + res);
				Log.d("test", "9");
				if (res == 200) {
					Log.e(TAG, "request success");
					InputStream input = connection.getInputStream();
					StringBuffer sb1 = new StringBuffer();
					int ss;
					while ((ss = input.read()) != -1) {
						sb1.append((char)ss);
					}
					
					result = sb1.toString();
					Log.d(TAG, "result: " + result);
										
					input.close();
				}
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return result;
		
	}
}
