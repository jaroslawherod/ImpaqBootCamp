package boot.camp.csv;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boot.camp.csv.model.PersonConverter;
import boot.camp.csv.model.Person;

public class CSVParserTest {

	InputStream inputStream = null;
	List<Person> expected = new ArrayList<Person>();
	CSVParser<Person> parser = new CSVParser<Person>(new PersonConverter());
	PersonConverter converter = new PersonConverter();
	byte[] bytes = null; 

	@Before
	public void initialize() {
		String text = "Jaroslaw Herod,800805123456,ul. Bura 15 60-222 Poznan\nJan kowalski,851205123456,ul. Nijaka 15 01-222 Warszawa";
		bytes = text.getBytes();
		inputStream = new ByteArrayInputStream(bytes);
	}

	@Test
	public void testCorrectStream() throws CSVParserException, CSVConverterException {
		List<Person> actual = parser.parse(inputStream, ",");
		List<Person> expected = new ArrayList<Person>();
		expected.add(new Person("Jaroslaw Herod", "800805123456",
				"ul. Bura 15 60-222 Poznan"));
		expected.add(new Person("Jan kowalski", "851205123456",
				"ul. Nijaka 15 01-222 Warszawa"));
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}

	}

	@Test(expected=CSVConverterException.class)
	public void testWrongSeparator() throws CSVConverterException, CSVParserException {
		parser.parse(inputStream, ";");
	}

	@Test(expected=CSVConverterException.class)
	public void testClosedStream() throws CSVParserException, IOException, CSVConverterException {
		inputStream.close();
		parser.parse(inputStream, ";");

	}

}