package boot.camp.csv;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CSVParserTest {

	InputStream fileInputStream = null;
	List<Person> expected = new ArrayList<Person>();
	CSVParser<Person> parser = new CSVParser<Person>(new PersonConverter());
	PersonConverter converter = new PersonConverter();

	@Before
	public void initialize() {
		ClassLoader loader = this.getClass().getClassLoader();
		fileInputStream = loader.getResourceAsStream("test.csv");
	}

	@Test
	public void testCorrectFile() throws CSVParserException, CSVConverterException {
		List<Person> actual = parser.parse(fileInputStream, ",");
		List<Person> expected = new ArrayList<Person>();
		expected.add(new Person("Jarosław Heród", "800805123456",
				"ul. Bura 15 60-222 Poznań"));
		expected.add(new Person("Jan kowalski", "851205123456",
				"ul. Nijaka 15 01-222 Warszawa"));
		assertEquals(expected.size(), actual.size());
		for (int i = 0; i < actual.size(); i++) {
			assertEquals(expected.get(i), actual.get(i));
		}

	}

	@Test(expected=CSVConverterException.class)
	public void testWrongSeparator() throws CSVConverterException, CSVParserException {
		parser.parse(fileInputStream, ";");
	}

	@Test(expected=CSVParserException.class)
	public void testClosedStream() throws CSVParserException, IOException, CSVConverterException {
		fileInputStream.close();
		parser.parse(fileInputStream, ";");

	}

}