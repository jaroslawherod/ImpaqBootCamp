package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
	
	public List<Container> parse(Reader reader) throws IOException
	{
		List<Container> personslist = new ArrayList<Container>();
		
		BufferedReader BR = new BufferedReader( reader );
		
		String line = "";
		Container person;

		while ( (line = BR.readLine()) != null )
		{
			person = new Container();
			String[] tmp = line.split(",");

			person.setName(tmp[0].trim());
			person.setId(tmp[1].trim());
			person.setAddress(tmp[2].trim());			

			personslist.add(person);
		}

		return personslist;
		

	}

}
