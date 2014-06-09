package boot.camp.repository;

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
import boot.camp.csv.model.Person;
import boot.camp.csv.model.PersonConverter;

public class CSVRepository implements IRepository {

	private CSVParser<Person> parser;
	private CSVWriter<Person> writer;
	private File file = null;
	private String separator = ",";

	public CSVRepository() {
		String filename = System.getProperty("user.home") + "\\repository.csv";
		file = new File(filename);
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getAbsolutePath());
		CSVConverter<Person> converter = new PersonConverter();
		parser = new CSVParser<Person>(converter);
		writer = new CSVWriter<Person>(converter);
	}

	public List<Person> getPeople() throws RepositoryException {
		InputStream stream = null;
		List<Person> people = null;
		try {
			stream = new FileInputStream(file);
			people = parser.parse(stream, separator);
		} catch (FileNotFoundException e) {
			throw new RepositoryException(e);
		} catch (CSVParserException e) {
			throw new RepositoryException(e);
		} catch (CSVConverterException e) {
			throw new RepositoryException(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new RepositoryException(e);
				}
			}
		}
		return people;

	}

	public void savePerson(Person person) throws RepositoryException {
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(file, true);
			writer.write(stream, person, separator, true);
		} catch (FileNotFoundException e) {
			throw new RepositoryException(e);
		} catch (CSVWriterException e) {
			throw new RepositoryException(e);
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					throw new RepositoryException(e);
				}
			}
		}
	}
}
