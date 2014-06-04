package boot.camp.day1.hello;

import static org.junit.Assert.assertThat;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ParserTest {
	
	@Test
	public void testParser1() throws IOException
	{
		try
		{
			List<Person> people = new ArrayList<Person>();
			
			Parser parser = new Parser();
			people = parser.parse(new FileReader("src/test/resources/test.csv"));
			
			assertThat(!people.isEmpty(), CoreMatchers.is(true));
		}
		catch(IOException e)
		{
			
		}
	}
}
