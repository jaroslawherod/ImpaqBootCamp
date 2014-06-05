package day3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;



public class CSVParser {
	
	public CSVParser(){ 
	}


public LinkedList<Person> preprocessCSVFile(InputStream fp) throws CSVException, IOException{
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