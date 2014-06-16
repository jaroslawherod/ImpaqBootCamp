package repository.csvservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import contactscsvservice.Contact;
import contactscsvservice.CsvReader;
import contactscsvservice.CsvWriter;
import repository.factory.*;

public class PersonWebAppCsvRepository implements Repository {

	private String path;

	public PersonWebAppCsvRepository(String path) {
		this.path = path;
	}

	public List<Contact> findAll() throws Exception {
		CsvReader csvReader;
		Reader fileReader = null;
		List<Contact> people = null;

		try {

			csvReader = new CsvReader();
			fileReader = new FileReader(new File(this.path));
			people = csvReader.read(fileReader);

		} catch (RepositoryCsvException e) {
			throw new RepositoryCsvException("Błąd repozytorium. ", e);
		} catch (Exception e) {
			throw new Exception("Nieznany błąd. ", e);
		}
		finally {
			if(fileReader != null)
				fileReader.close();
		}

		return people;
	}

	public void save(List<Contact> people) throws Exception {
		Writer writer = null;
		CsvWriter csvWriter = null;
		Boolean append = null;

		try {

			File file = new File(this.path);
			append = file.exists() ? true : false;
			writer = new FileWriter(this.path, append);
			csvWriter = new CsvWriter();
			csvWriter.save(writer, people);

		} catch (FileNotFoundException fileException) {
			throw new RepositoryCsvException("Nie znaleziono pliku. Sprawdź czy wprowadzona ścieżka jest poprawna: ",fileException);
		} catch (Exception e) {
			throw new Exception("Nieznany błąd: ", e);
		} finally {
			if (writer != null)
				writer.close();
		}

	}

}
