package boot.camp.day1.hello;

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

public class CSVParser {

	public List<Person> parse(InputStream stream, String separator)
			throws CSVParserException {
		InputStreamReader streamReader = new InputStreamReader(stream);
		BufferedReader reader = new BufferedReader(streamReader);
		List<Person> parsedPersons = new ArrayList<Person>();
		String line = null;

		try {
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(separator);
				Person person = new Person(values[0], values[1], values[2]);
				parsedPersons.add(person);
			}
		} catch (IOException e) {
			throw new CSVParserException("Stream is closed", e);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new CSVParserException("Invalid file structure.");
		}

		return parsedPersons;

	}

	public static void main(String[] args) {
		File file = new File("test.csv");
		InputStream stream = null;
		CSVParser parser = new CSVParser();

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
