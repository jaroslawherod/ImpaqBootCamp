package boot.camp.day1.hello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Person> parse(Reader fr) throws IOException, ParserException {
	BufferedReader br = null;

	List<Person> people = new ArrayList<Person>();
	try {
	    br = new BufferedReader(fr);

	    String line = "";
	    while ((line = br.readLine()) != null) {

		String[] tmp = line.split(",");

		if (tmp.length == 3)
		    people.add(new Person(tmp[0], tmp[1], Integer
			    .parseInt(tmp[2])));
		else
		    throw new ParserException("Wrong number of parameters.");
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} finally {
	    if (fr != null)
		fr.close();
	    if (br != null)
		br.close();
	}
	return people;
    }
}