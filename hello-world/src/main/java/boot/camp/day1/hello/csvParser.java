package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class csvParser {
	private String filePath;
	private BufferedReader fileReader=null;
	private String csvSeparator=",";
	private String line="";
	private String[] person;
	private Person newPerson;
	
	public csvParser(String fp){
		try {
			this.filePath=fp;
			
			fileReader=new BufferedReader(new FileReader(filePath));
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Nie znaleziono pliku");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

public void preprocessCSVFile(){
	try {
		while ((line=fileReader.readLine())!=null){
			person=line.split(csvSeparator);
			newPerson=this.makePerson();
			newPerson.printPerson();
			System.out.println("____________\n");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		if (fileReader!=null)
			try {
				fileReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
	
public Person makePerson(){
		return new Person(person[0],person[1],person[2]);
	}
}