package boot.camp.csvservlet.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import boot.camp.csv.CSVConverter;
import boot.camp.csv.CSVConverterException;
import boot.camp.csv.CSVParser;
import boot.camp.csv.CSVParserException;
import boot.camp.csv.CSVWriter;
import boot.camp.csv.CSVWriterException;
import boot.camp.csv.Person;
import boot.camp.csv.PersonConverter;

public class CSVRepository {
	
	
	CSVParser<Person> parser;
	CSVWriter<Person> writer;
	File file = null;
	String separator = ",";
	
	public CSVRepository() {
		String filename = System.getProperty("user.home") + "\\repository.csv";
		file = new File(filename);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsolutePath());
		CSVConverter<Person> converter = new PersonConverter();
		parser = new CSVParser<Person>(converter);
		writer = new CSVWriter<Person>(converter);
	}
	
	public List<Person> getPeople() throws CSVParserException, CSVConverterException, IOException {
		InputStream stream = new FileInputStream(file);
		List<Person> people = parser.parse(stream, separator);
		stream.close();
		return people;
		
	}
	
	public void savePerson(Person person) throws CSVWriterException, IOException {
		OutputStream stream = new FileOutputStream(file, true);
		writer.write(stream, person, separator, true);
		stream.close();		
	}
}
