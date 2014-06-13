package contactscsvservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class MainClass {
	public MainClass() throws IOException, ContactsCsvServiceException {
		Boolean append;
		String pathToFile, pathNewFile;
		Reader reader = null;
		CsvReader csvReader = null;
		
		Writer writer = null;
		CsvWriter csvWriter = null;

		try {
			pathToFile = "src//test//resources//sample.csv";
			pathNewFile = "src//test//resources//newcsv.csv";
			
			reader = new FileReader(new File(pathToFile));
			csvReader = new CsvReader();
			List<Contact> contactsList = csvReader.read(reader);

			File checkExist = new File(pathNewFile);
			append = checkExist.exists() ? true : false;
			writer = new FileWriter(pathNewFile, append);
			
			csvWriter = new CsvWriter();
			csvWriter.save(writer, contactsList);

		} catch (ContactsCsvServiceException formatException) {
			throw new ContactsCsvServiceException("Nie właściwy format pliku. ");
		} catch (FileNotFoundException fileex) {
			throw new ContactsCsvServiceException("Nie znaleziono pliku. Sprawdź czy wprowadzona ścieżka jest poprawna. ",
					fileex.getCause());
		} catch (Exception e) {
			throw new ContactsCsvServiceException( "Nieznany błąd. ", e.getCause() );
		} finally {
			if (reader != null) reader.close();
			if (writer != null)	writer.close();
		}

	}
}
