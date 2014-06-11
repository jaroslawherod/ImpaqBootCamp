package boot.camp.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import boot.camp.csv.model.PersonConverter;
import boot.camp.csv.model.Person;

public class CSVParser<T> {
	
	CSVConverter<T> converter;
	
	
	public CSVParser(CSVConverter<T> converter) {
		super();
		this.converter = converter;
	}

	public List<T> parse(InputStream stream, String separator)
			throws CSVParserException, CSVConverterException {
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);
		List<T> parsed = new ArrayList<T>();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(separator);
				parsed.add(converter.convertBack(Arrays.asList(values)));
			}
		} catch (IOException e) {
			throw new CSVParserException("Stream is closed", e);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CSVParserException("Invalid file structure.");
		}

		return parsed;

	}

	public static void main(String[] args) {
		File file = new File("test.csv");
		InputStream stream = null;
		CSVConverter<Person> converter = new PersonConverter();
		CSVParser<Person> parser = new CSVParser<Person>(converter);

		try {
			stream = new FileInputStream(file);
			System.out.println("Oddzielone przecinkiem:");
			System.out.println(parser.parse(stream, ","));

			stream = new FileInputStream(file);
			System.out.println("Oddzielone średnikiem:");
			System.out.println(parser.parse(stream, ";"));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSVParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CSVConverterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
