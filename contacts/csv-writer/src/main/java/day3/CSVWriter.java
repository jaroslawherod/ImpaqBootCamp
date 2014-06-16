package day3;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


public class CSVWriter {

	public CSVWriter(){
		
	}

	public void writePersonsToStream(OutputStream stream, List<Person> personsList) throws CSVException, IOException{
		Person readPerson;
		StringBuilder sb = new StringBuilder();
		String separator=",";
		
		for(int i=0; i<personsList.size(); i++){
			readPerson=personsList.get(i);
			sb.append(readPerson.getName());
			sb.append(separator);
			sb.append(readPerson.getId());
			sb.append(separator);
			sb.append(readPerson.getAdres());
			sb.append(System.lineSeparator());	
		}
		stream.write(sb.toString().getBytes());				
	}
	
	public void writeOnePersonToStream(OutputStream stream, Person person) throws CSVException, IOException{
		StringBuilder sb = new StringBuilder();
		String separator=",";
		
			sb.append(person.getName());
			sb.append(separator);
			sb.append(person.getId());
			sb.append(separator);
			sb.append(person.getAdres());
			sb.append(System.lineSeparator());	
		
		stream.write(sb.toString().getBytes());				
	}
}

