package com.web.json;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		out.write(gson.toJson(csvParser.preprocessCSVFile(file)));
		out.flush();
		out.close();
    }	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*PrintWriter os=new PrintWriter("C:/cos.txt");
		BufferedReader isr=request.getReader();
		
		
			os.write(isr.readLine());
		
		os.close();*/
		Gson gson=new Gson();
		InputStream readStream=request.getInputStream();
		ServletContext context = getServletContext();
		CSVWriter csvWriteFile=new CSVWriter();
		Person newPerson = new Person();
		String fullPath = context.getRealPath("inputdata.csv");
		List<Person> personsList=new LinkedList<Person>();
	
		InputStreamReader stream=new InputStreamReader(readStream);
		OutputStream fileWriter=new FileOutputStream(fullPath);
		JsonReader js=new JsonReader(stream);
		js.beginArray();
		while(js.hasNext())
		personsList.add(gson.fromJson(stream, Person.class));
		js.endArray();
		newPerson.setId("9192938123");
		newPerson.setName("Nowe Nazwisko");
		newPerson.setAdres("ul. jajaksd 12 61-231 Poznan");
		personsList.add(newPerson);
		csvWriteFile.writePersonsToStream(fileWriter, personsList);
		js.close();
    }	
}
