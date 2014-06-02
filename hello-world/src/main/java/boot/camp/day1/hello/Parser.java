package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

	public List<Person> parse(BufferedReader br) throws IOException {
		List<Person> people = new ArrayList<Person>();
		
		String line = "";
		while ((line = br.readLine()) != null) {
 
			String[] tmp = line.split(",");
			
			if(tmp.length == 3)
				people.add(new Person(tmp[0],tmp[1],Integer.parseInt(tmp[2])));
		}
		
		return people;
	}
}