package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
	
	List<Person> people = new ArrayList<Person>();
	
	public static void main(String[] args) {
		List<Person> people;
		
		Parser parser;
		BufferedReader br;
		FileReader fr;
		try
		{
			// Troche inny ten plik z danymi (testowo int)
			fr = new FileReader("test.csv");
			br = new BufferedReader(fr);
			parser = new Parser();
			
			people = parser.parse(br);
			
			br.close();
			fr.close();
			
			for(Person tmp : people) {
				System.out.println(tmp.getImie() + " " + tmp.getNazwisko() + " " + tmp.getWiek());
			}
		}
		catch(IOException e)
		{
			
		}
	}


}