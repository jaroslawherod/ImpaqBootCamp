package boot.camp.day1.hello;

import static org.junit.Assert.assertThat;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ParserTest {
	
	@Test
	public void shouldReturnNotEmptyListFromFileStream() throws IOException
	{
		List<Person> people = new ArrayList<Person>();
		try
		{
			Parser parser = new Parser();
			people = parser.parse(new FileReader("src/test/resources/test.csv"));
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(ParserException e)
		{
			System.out.println(e.getErrorName());
		}
		assertThat(!people.isEmpty(), CoreMatchers.is(true));
	}
	
	@Test
	public void shouldReturnNotEmptyListFromStringStream() throws IOException
	{
		List<Person> people = new ArrayList<Person>();
		try
		{
			Parser parser = new Parser();
			people = parser.parse(new StringReader("Dawid,Chroscielski,20"));
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(ParserException e)
		{
			System.out.println(e.getErrorName());
		}
		assertThat(!people.isEmpty(), CoreMatchers.is(true));
	}
	
	@Test
	public void shouldReturnNotEmptyListFromStringStreamWithValidValues() throws IOException
	{
		List<Person> people = new ArrayList<Person>();
		try
		{
			Parser parser = new Parser();
			people = parser.parse(new StringReader("Dawid,Chroscielski,20"));
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(ParserException e)
		{
			System.out.println(e.getErrorName());
		}

		assertThat(!people.isEmpty(), CoreMatchers.is(true));
		assertThat(people.get(0).getImie().equals("Dawid"), CoreMatchers.is(true));
		assertThat(people.get(0).getNazwisko().equals("Chroscielski"), CoreMatchers.is(true));
		assertThat(people.get(0).getWiek() == 20, CoreMatchers.is(true));
	}
}
