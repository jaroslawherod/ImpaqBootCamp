package day3;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class CSVWriterTest {
	
	@Test
	public void shouldProperlyReadDataFromListAndPrintItToStream() throws CSVException, IOException {
		List<Person> personsList = new LinkedList<Person>();
		Person newPerson=new Person("qwe asd","asd asd","zxc zxc");
		Person newPerson2=new Person("qwe2","asd2","zxc2");
		personsList.add(newPerson);
		personsList.add(newPerson2);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		
		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writePersonsToStream(os,personsList);
		assertThat(os.toString(),CoreMatchers.equalTo("qwe asd,asd asd,zxc zxc\r\nqwe2,asd2,zxc2\r\n"));		
	}	
	
	@Test
	public void shouldProperlyReadListFromStreamAndWriteItToStream() throws CSVException, IOException {
		CSVParser parser=new CSVParser();
		InputStream fp= new ByteArrayInputStream("Imie Nazwisko, Id jakies, Adres jakis".getBytes());
		List<Person> personsList = parser.preprocessCSVFile(fp);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		
		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writePersonsToStream(os,personsList);
		assertThat(os.toString(),CoreMatchers.equalTo("Imie Nazwisko, Id jakies, Adres jakis\r\n"));		
	}	
	
	@Test
	public void shouldProperlyReadListFromCSVFileUsingCSVParserAndWriteItToStream() throws CSVException, IOException {
		CSVParser parser=new CSVParser();
		InputStream fp= new FileInputStream("C:/praktyki.csv");
		List<Person> personsList = parser.preprocessCSVFile(fp);
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		
		CSVWriter csvWriter = new CSVWriter();
		csvWriter.writePersonsToStream(os,personsList);
		assertThat(os.toString(),CoreMatchers.equalTo("Jaroslaw Herod,800805123456, ul. Bura 15 60-222 Poznan\r\n"
				+"Jan kowalski,851205123456, ul. Nijaka 15 01-222 Warszawa\r\n"
				+"Jakub Daze,8141231231412, ul. Dziwna 14 02-132 Poznan\r\n"));		
	}	
	
}
