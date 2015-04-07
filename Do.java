package com.chengl.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Do extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int i = 0;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("This is <POST> request START");
		ServletInputStream in = req.getInputStream();
		System.out.println(in.toString());
		byte[] buffer = new byte[1024];
		File file = new File("E:\\servicenote.json");
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("create new file");
		}
		FileOutputStream out = new FileOutputStream(file);
		int len = in.read(buffer, 0, 1024);
		while(len != -1) {
			out.write(buffer, 0, len);
			len = in.readLine(buffer, 0, 1024);
		}
		System.out.println("This is <POST> request END");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("This is <PUT> request, the name is :" + req.getParameter("name"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getOutputStream().println("This is <GET> request, the name is :" + req.getParameter("name"));
	}

}
