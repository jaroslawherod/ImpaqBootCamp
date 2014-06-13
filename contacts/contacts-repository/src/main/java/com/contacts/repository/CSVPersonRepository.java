package com.contacts.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import com.contacts.web.*;

public class CSVPersonRepository implements PersonRepository{
	private CSVParser parser;
	private CSVWriter writer;
	private InputStream fileInputStream;
	private OutputStream fileOutputStream;
	
	public CSVPersonRepository(){
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
	
	public void createPerson(Person person){
		try {
			writer.writeOnePersonToStream(fileOutputStream, person);
		} catch (CSVException e) {
			throw new RepositoryException(e);
		} catch (IOException e) {
			throw new RepositoryException(e);
		}
	}
	public List<Person> findAllPersons(){
		try {
			return parser.preprocessCSVFile(fileInputStream);
		} catch (CSVException e) {
			throw new RepositoryException(e);
		} catch (IOException e) {
			throw new RepositoryException(e);
		}
	}
}
