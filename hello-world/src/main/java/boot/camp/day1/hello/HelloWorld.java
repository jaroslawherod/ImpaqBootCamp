package boot.camp.day1.hello;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
	
	List<Person> people = new ArrayList<Person>();
	
	public static void main(String[] args) {
		List<Person> people;
		
		Parser parser;
		try
		{
			// Troche inny ten plik z danymi (testowo int)
			parser = new Parser();
			
			people = parser.parse(new FileReader("src/test/resources/test.csv"));
			
			for(Person tmp : people) {
				System.out.println(tmp.getImie() + " " + tmp.getNazwisko() + " " + tmp.getWiek());
			}
		}
		catch(IOException e)
		{
			
		}
		catch(ParserException e)
		{
			System.out.println(e.getErrorName());
		}
	}


}