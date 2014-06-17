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

import com.contacts.repository.CSVPersonRepository;
import com.contacts.repository.H2PersonRepository;
import com.contacts.service.PersonService;
import com.contacts.service.PersonServiceImpl;
import com.google.gson.Gson;

import day3.*;


public class Model {
	
	private PersonService csvPersonService=new PersonServiceImpl(new CSVPersonRepository());
	private PersonService h2PersonService=new PersonServiceImpl(new H2PersonRepository());
	
	public PersonService getCsvPersonService() {
		return csvPersonService;
	}

	public PersonService getH2PersonService() {
		return h2PersonService;
	}

	private List<Person> csvPersonsList=new LinkedList<Person>();
	private List<Person> h2PersonsList=new LinkedList<Person>();

	public void printJson(PrintWriter out) throws CSVException, FileNotFoundException, IOException{
		csvPersonsList=csvPersonService.findAllPersons();
		h2PersonsList=h2PersonService.findAllPersons();
		Gson gson=new Gson();
		out.write(gson.toJson(csvPersonsList)+System.lineSeparator()+gson.toJson(h2PersonsList)+System.lineSeparator());
		out.flush();
		out.close();
	}
	
	public Person validJson(BufferedReader br) throws IOException{
		Gson gson=new Gson();
		try{
			return gson.fromJson(br, Person.class);
		}
		catch(com.google.gson.JsonSyntaxException e){
			throw new JsonException("Nieprawidlowy format danych",e);
		}
	}
}
