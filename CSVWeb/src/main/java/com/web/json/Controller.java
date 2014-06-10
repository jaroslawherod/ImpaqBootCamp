package com.web.json;


import java.io.BufferedReader;
import java.io.FileInputStream;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class Controller extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = getServletContext();
		String fullPath = context.getRealPath("inputdata.csv");
		InputStream file=new FileInputStream(fullPath);
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		CSVParser csvParser=new CSVParser();
		List <Person> personsList=csvParser.preprocessCSVFile(file);
		out.write(gson.toJson(personsList));
		out.flush();
		out.close();
    }	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Gson gson=new Gson();
		ServletContext context = getServletContext();
		BufferedReader readStream=request.getReader();
		CSVWriter csvWriteFile=new CSVWriter();
		String fullPath = context.getRealPath("inputdata.csv");
		List<Person> personsList=new LinkedList<Person>();
		OutputStream fileWriter=new FileOutputStream(fullPath,true);
		JsonReader js=new JsonReader(readStream);
		personsList.add(gson.fromJson(readStream, Person.class));
		csvWriteFile.writePersonsToStream(fileWriter, personsList);
		js.close();
    }	
}
