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
    
	public Person validJson(BufferedReader br) throws IOException{
		Gson gson=new Gson();
		try{
			return gson.fromJson(br, Person.class);
		}
		catch(com.google.gson.JsonSyntaxException e){
			throw new JsonException("Nieprawidlowy format danych",e);
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String path = System.getProperty("user.home");
		String fullPath=path+"/inputdata.csv";
		try{
		InputStream file=new FileInputStream(fullPath);
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		CSVParser csvParser=new CSVParser();
		List <Person> personsList=csvParser.preprocessCSVFile(file);
		out.write(gson.toJson(personsList));
		out.flush();
		out.close();
		}
		catch(FileNotFoundException e){
			throw new CSVException("Brak pliku inputdata.csv w katalogu uzytkownika", e);
		}
    }	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader readStream=request.getReader();
		CSVWriter csvWriteFile=new CSVWriter();
		String path = System.getProperty("user.home");
		String fullPath=path+"/inputdata.csv";
		List<Person> personsList=new LinkedList<Person>();
		OutputStream fileWriter=new FileOutputStream(fullPath,true);
		personsList.add(validJson(readStream));
		csvWriteFile.writePersonsToStream(fileWriter, personsList);
	}	
}
