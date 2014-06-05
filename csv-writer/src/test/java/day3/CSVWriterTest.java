package day3;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class CSVWriterTest {
	@Test
	public void shouldProperlyReadDataFromList() throws CSVException, IOException {
		List<Person> personsList = new LinkedList<Person>();
		Person newPerson=new Person("qwe","asd","zxc");
		Person newPerson2=new Person("qwe2","asd2","zxc2");
		personsList.add(newPerson);
		personsList.add(newPerson2);
		String os;
		
		CSVWriter csvWriter = new CSVWriter();
		os=csvWriter.writePersonsToStream(System.out,personsList);
		assertThat(os,CoreMatchers.equalTo("qwe,asd,zxc\r\nqwe2,asd2,zxc2\r\n"));	
		
	}	
}
