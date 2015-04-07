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

import android.util.Log;

public class UploadUtil {
	private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 10 * 1000;
	private static final String CHARSET = "utf-8";
	
	public static String uploadFile(File file, String requestURL) {
		
		String result = "";
		String BOUNDARY = UUID.randomUUID().toString();
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data";
		
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
			connection.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" +BOUNDARY);
			Log.d("test", "1");
			
			if (file != null) {
				Log.d("test", "2");
				DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
				StringBuffer sb = new StringBuffer();
				Log.d("test", "3");
				//sb.append(PREFIX);
				//sb.append(BOUNDARY);
				//sb.append(LINE_END);
				Log.d("test", "4");
				String test = "Content-Disposition: form-data; " + LINE_END;
				Log.d(TAG, test);
				Log.d("test", "5");
				//sb.append("Content-Disposition: form-data; " + LINE_END);
				//sb.append(LINE_END);
				Log.d("test", "6");
				//dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				Log.d("test", "7");
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
				//dos.write(end_data);
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
