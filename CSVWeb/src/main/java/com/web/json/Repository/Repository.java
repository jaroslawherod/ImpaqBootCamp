package com.web.json.Repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.web.json.CSVException;
import com.web.json.CSVParser;
import com.web.json.CSVWriter;
import com.web.json.Person;


public class Repository {
	private CSVParser parser;
	private CSVWriter writer;
	private InputStream fileInputStream;
	private OutputStream fileOutputStream;
	
	public Repository(){
		String filePath=System.getProperty("user.home")+"/inputdata.csv";
		try {
			fileInputStream=new FileInputStream(filePath);
		} catch (FileNotFoundException e1) {
			throw new CSVException("Brak ścieżki",e1);
		}
		try {
			fileOutputStream=new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			throw new CSVException("Brak ścieżki",e);
		}
		parser=new CSVParser();
		writer=new CSVWriter();
	}
	
	public void savePerson(Person person) throws CSVException, IOException{
		writer.writeOnePersonToStream(fileOutputStream, person);
	}
	public List<Person> getPersons() throws CSVException, IOException{
		return parser.preprocessCSVFile(fileInputStream);
	}
}
