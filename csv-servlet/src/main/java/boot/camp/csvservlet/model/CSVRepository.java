package boot.camp.csvservlet.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	public CSVRepository(String filename) {
		file = new File("data.csv");
		CSVConverter<Person> converter = new PersonConverter();
		parser = new CSVParser<Person>(converter);
		writer = new CSVWriter<Person>(converter);
	}
	
	public List<Person> getPeople() throws CSVParserException, FileNotFoundException, CSVConverterException {
		InputStream stream = new FileInputStream(file);
		return parser.parse(stream, separator);
		
	}
	
	public void savePerson(Person person) throws FileNotFoundException, CSVWriterException {
		OutputStream stream = new FileOutputStream(file);
		writer.write(stream, person, separator);
		
	}
}
