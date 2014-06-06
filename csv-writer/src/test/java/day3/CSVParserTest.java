package day3;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class CSVParserTest {

	@Test
	public void shouldProperlyReadFileStreamWithSinglePerson() throws CSVException, IOException {
		//stworzniea strumienia
		ByteArrayInputStream strumien=new ByteArrayInputStream("jakub,daze,xxxx".getBytes());
		
		
		// odczytanie listy osób
		CSVParser parser=new CSVParser();
		List<Person> list=parser.preprocessCSVFile(strumien);
		
		Person person = list.get(0);
		assertThat(person.getName(), CoreMatchers.equalTo("jakub"));
		assertThat(person.getId(), CoreMatchers.equalTo("daze"));
		assertThat(person.getAdres(), CoreMatchers.equalTo("xxxx"));
		
	}
	
	@Test
	public void shouldProperlyReadStreamWithTwoPersons() throws CSVException, IOException {
		//stworzniea strumienia
				ByteArrayInputStream strumien1=new ByteArrayInputStream("jakub,daze,xxxx\njakub2,daze2,xxxx2".getBytes());
				
				// odczytanie listy osób
				CSVParser parser=new CSVParser();
			
				List<Person> list=parser.preprocessCSVFile(strumien1);
				
				assertThat(list.size(), CoreMatchers.equalTo(2));			
	}
	
	@Test
	public void shouldProperlyReadDataFromStreamWithTwoPersons() throws CSVException, IOException{
		ByteArrayInputStream strumien1=new ByteArrayInputStream("jakub,daze,xxxx\njakub2,daze2,xxxx2".getBytes());
		
		// odczytanie listy osób
		CSVParser parser=new CSVParser();
	
		List<Person> list=parser.preprocessCSVFile(strumien1);
		
		Person person = list.get(0);
		assertThat(person.getName(), CoreMatchers.equalTo("jakub"));
		assertThat(person.getId(), CoreMatchers.equalTo("daze"));
		assertThat(person.getAdres(), CoreMatchers.equalTo("xxxx"));
		
		person = list.get(1);
		assertThat(person.getName(), CoreMatchers.equalTo("jakub2"));
		assertThat(person.getId(), CoreMatchers.equalTo("daze2"));
		assertThat(person.getAdres(), CoreMatchers.equalTo("xxxx2"));
	}
	
}
