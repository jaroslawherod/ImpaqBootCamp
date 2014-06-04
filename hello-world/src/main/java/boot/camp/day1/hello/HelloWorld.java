package boot.camp.day1.hello;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class HelloWorld
{

	public static void main(String[] args) throws IOException
	{
		String patch = "src//test//resources//sample.csv";
		Reader reader = new FileReader(new File(patch));
		
		Parser person = new Parser();
		List<Container> l = person.parse(reader);
		
		reader.close();
		
		/*
		wyswietlanie list (test sprawdzenia zawarosci listy)
		for (Container x : l) { System.out.println("Id: " + x.getId());
		System.out.println("Name: " + x.getName());
		System.out.println("Address: " + x.getAddress());
		System.out.println(); }
		*/
		
	}
}
