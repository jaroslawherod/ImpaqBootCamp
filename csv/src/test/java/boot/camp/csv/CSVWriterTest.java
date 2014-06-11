package boot.camp.csv;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boot.camp.csv.model.PersonConverter;
import boot.camp.csv.model.Person;

public class CSVWriterTest {

	List<Person> persons = null;
	ByteArrayOutputStream stream = null;
	CSVWriter<Person> writer = new CSVWriter<Person>(new  PersonConverter());
	CSVConverter<Person> converter = new PersonConverter();
	

	
	@Before
	public void initialize() {
		stream = new ByteArrayOutputStream();
		persons = new ArrayList<Person>();
		persons.add(new Person("Jaroslaw Herod", "800805123456",
				"ul. Bura 15 60-222 Poznan"));
		persons.add(new Person("Jan kowalski", "851205123456",
				"ul. Nijaka 15 01-222 Warszawa"));
	}
	
	@Test
	public void writeTest() {
		String expected = "Jaroslaw Herod,800805123456,ul. Bura 15 60-222 Poznan\nJan kowalski,851205123456,ul. Nijaka 15 01-222 Warszawa";
		try {
			writer.write(stream, persons, ",", false);
			try {
				stream.close();
				String actual = stream.toString("UTF-8");
				assertEquals(expected, actual);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (CSVWriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test(expected=CSVWriterException.class)
	public void closedStreamTest() throws CSVWriterException, IOException {
		File file = new File("output.csv");
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(file);
			outputStream.close();
			writer.write(outputStream, persons, ",", false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
