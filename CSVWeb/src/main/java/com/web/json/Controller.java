package com.web.json;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.gson.Gson;

public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Model model=new Model();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		model.printJson(out);
    }	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader readStream=request.getReader();
		Model model=new Model();
		model.addPerson(readStream);
	}	
}
