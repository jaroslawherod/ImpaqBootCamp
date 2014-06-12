package com.web.json;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;



public class CSVParser {
	
	public CSVParser(){ 
	}

	public List<Person> preprocessCSVFile(InputStream fp) throws CSVException, IOException{
	if (fp.available()==0) throw new CSVException("Brak strumienia lub strumień pusty");
	String line="";
	String[] person;
	Person newPerson;
	LinkedList<Person> personalData = new LinkedList<Person>();
	BufferedReader br = new BufferedReader(new InputStreamReader(fp));
		while ((line=br.readLine())!=null){
			person=line.split(",");
			newPerson=new Person(person[0],person[1],person[2]);
			personalData.addLast(newPerson);		
		}
	return personalData;
	}
}