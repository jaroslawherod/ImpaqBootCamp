package day3;

import java.util.List;
import java.io.IOException;
import java.io.OutputStream;


public class CSVWriter {

	public CSVWriter(){
		
	}
	
	public void writePersonsToStream(OutputStream stream, List<Person> personsList) throws CSVException, IOException{
		
		Person readPerson;
		String output="";
		StringBuilder sb = new StringBuilder();
		String separator=",";
		
		
		for(int i=0; i<personsList.size(); i++){
			readPerson=personsList.get(i);
			output=readPerson.getName()+separator+readPerson.getId()+separator+readPerson.getAdres();
			sb.append(output);
			sb.append(System.getProperty("line.separator"));
			output=sb.toString();	
		}
		stream.write(output.getBytes());				
	}
}
