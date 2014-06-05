package boot.camp.day1.hello;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CVSParserTest {

//	InputStream fileInputStream = null;
//	List<Person> expected = new ArrayList<Person>();
//	CSVParser parser = new CSVParser();
//
//	@Before
//	public void initialize() {
//		ClassLoader loader = this.getClass().getClassLoader();
//		fileInputStream = loader.getResourceAsStream("test.csv");
//	}
//
//	@Test
//	public void testCorrectFile() throws CSVParserException {
//		List<Person> actual = parser.parse(fileInputStream, ",");
//		List<Person> expected = new ArrayList<Person>();
//		expected.add(new Person("Jarosław Heród", "800805123456",
//				"ul. Bura 15 60-222 Poznań"));
//		expected.add(new Person("Jan kowalski", "851205123456",
//				"ul. Nijaka 15 01-222 Warszawa"));
//		assertEquals(expected.size(), actual.size());
//		for (int i = 0; i < actual.size(); i++) {
//			assertEquals(expected.get(i), actual.get(i));
//		}
//
//	}
//
//	@Test
//	public void testWrongSeparator() {
//		try {
//			parser.parse(fileInputStream, ";");
//			fail();
//		} catch (CSVParserException e) {
//			assertEquals("Invalid file structure.", e.getMessage());
//		}
//
//	}
//
//	@Test(expected=CSVParserException.class)
//	public void testClosedStream() throws CSVParserException, IOException {
//		fileInputStream.close();
//		parser.parse(fileInputStream, ";");
//
//	}

}