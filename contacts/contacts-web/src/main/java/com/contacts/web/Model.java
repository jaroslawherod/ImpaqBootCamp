package com.contacts.web;

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

import com.google.gson.Gson;

import day3.*;


public class Model {
	
	private List<Person> personsList=new LinkedList<Person>();
	
	private String getPath(){
		String userHomePath=System.getProperty("user.home");
		return userHomePath+"/inputdata.csv";
	}
	
	private InputStream getInputStream(){
		try {
			return new FileInputStream(getPath());
		} catch (FileNotFoundException e) {
			throw new CSVException("Brak pliku",e);
		}
	}
	
	private List<Person> getCSVList(InputStream inputStream) throws CSVException, IOException{
		CSVParser csvParser=new CSVParser();
		return csvParser.preprocessCSVFile(inputStream);
	}
	
	public void printJson(PrintWriter out) throws CSVException, FileNotFoundException, IOException{
		personsList=getCSVList(getInputStream());
		Gson gson=new Gson();
		out.write(gson.toJson(personsList));
		out.flush();
		out.close();
	}
	
	private OutputStream getOutputStream(){
		try {
			return new FileOutputStream(getPath(),true);
		} catch (FileNotFoundException e) {
			throw new CSVException("Brak pliku",e);
		}
	}
	
	public void addPerson(BufferedReader br) throws CSVException, IOException {
		CSVWriter csvWriter=new CSVWriter();
		personsList.add(validJson(br));
		csvWriter.writePersonsToStream(getOutputStream(), personsList);
	}
	
	private Person validJson(BufferedReader br) throws IOException{
		Gson gson=new Gson();
		try{
			return gson.fromJson(br, Person.class);
		}
		catch(com.google.gson.JsonSyntaxException e){
			throw new JsonException("Nieprawidlowy format danych",e);
		}
	}
}
