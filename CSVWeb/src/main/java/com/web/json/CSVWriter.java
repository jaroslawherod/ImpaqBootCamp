package com.web.json;

import java.util.List;
import java.io.IOException;
import java.io.OutputStream;


public class CSVWriter {

	public CSVWriter(){
		
	}
	
	public void writePersonsToStream(OutputStream stream, List<Person> personsList) throws CSVException, IOException{
		Person readPerson;
		StringBuilder sb = new StringBuilder();
		String separator=",";
		
		for(int i=0; i<personsList.size(); i++){
			readPerson=personsList.get(i);
			//sb.append (readPerson.getName()  separator+readPerson.getId()  separator+readPerson.getAdres())
			sb.append(readPerson.getName());
			sb.append(separator);
			sb.append(readPerson.getId());
			sb.append(separator);
			sb.append(readPerson.getAdres());
			sb.append(System.lineSeparator());	
		}
		stream.write(sb.toString().getBytes());				
	}
}
