package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
	List<Container> list = new ArrayList<Container>();

	Parser(BufferedReader BR) throws IOException
	{
		this.Parse(BR);

	}

	private void Parse(BufferedReader BR) throws IOException
	{
		String line = "";
		Container person;

		while ((line = BR.readLine()) != null)
		{
			person = new Container();
			String[] tmp = line.split(",");

			person.setName(tmp[0].trim());
			person.setId(tmp[1].trim());
			person.setAddress(tmp[2].trim());			

			list.add(person);
		}

		/*
		 for (Container x : list) { System.out.println("Id: " + x.getId());
		 System.out.println("Name: " + x.getName());
		 System.out.println("Address: " + x.getAddress());
		 System.out.println(); }
		 */

	}

}
