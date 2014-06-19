package boot.camp.day1.hello;

import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class WriterTest {

    @Test
    public void shouldCreateNewFileIfItDoesnExist() throws IOException {
	List<Person> people = new ArrayList<Person>();

	File f = new File("src/test/resources/thisFileShouldBeCreated.csv");

	Parser parser = new Parser();
	Writer writer = new Writer();
	people = parser.parse(new StringReader("TestImie,TestNazw,20"));
	writer.write(people, new FileWriter(new File(
		"src/test/resources/thisFileShouldBeCreated.csv")));

	assertThat(f.exists() == true, CoreMatchers.is(true));
    }

    @Test(expected = WriterException.class)
    public void shouldThrowWriterExceptionIfListIsEmpty() throws IOException {
	List<Person> people = new ArrayList<Person>();

	Writer writer = new Writer();
	writer.write(people, new FileWriter(new File(
		"src/test/resources/thisShouldNeverHappen.csv")));
    }

    @Test
    public void shouldWriteValidDataToTestFile() throws IOException {
	String endl = System.getProperty("line.separator");

	List<Person> people = new ArrayList<Person>();

	Parser parser = new Parser();
	Writer writer = new Writer();

	File file = new File("src/test/resources/testFile.csv");

	people = parser.parse(new StringReader("TestImie,TestNazw,20" + endl
		+ "TestImie2,TestNazw2,30"));
	writer.write(people, new FileWriter(file));

	List<Person> checkingPeople = new ArrayList<Person>();
	checkingPeople = parser.parse(new FileReader(file));

	boolean isEqual = true;
	if (people.size() != checkingPeople.size())
	    isEqual = false;
	else {
	    for (int i = 0; i < people.size(); i++) {
		if (!checkingPeople.get(i).getImie()
			.equals((people.get(i).getImie()))
			|| !checkingPeople.get(i).getNazwisko()
				.equals((people.get(i).getNazwisko()))
			|| checkingPeople.get(i).getWiek() != people.get(i)
				.getWiek()) {
		    isEqual = false;
		    break;
		}
	    }
	}
	assertThat(isEqual, CoreMatchers.is(true));
    }
}