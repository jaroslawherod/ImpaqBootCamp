package contactscsvservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

	public List<Contact> read(Reader reader) throws Exception {

		List<Contact> contactList = new ArrayList<Contact>();
		BufferedReader bufferedReader = new BufferedReader(reader);
		try {
			String line = "";
			Contact person;

			while ((line = bufferedReader.readLine()) != null) {
				person = new Contact();
				String[] tmp = line.split(",");

				if (tmp.length != 3)
					throw new ContactsCsvServiceException();

				person.setName(tmp[0].trim());
				person.setId(tmp[1].trim());
				person.setAddress(tmp[2].trim());

				contactList.add(person);
			}
			
			return contactList;
		} catch (ContactsCsvServiceException formatException) {
			throw (new ContactsCsvServiceException("Nieprawodłowy plik *.csv: "	+ formatException.getMessage()));
		} catch (IndexOutOfBoundsException indexOutOfBoundsException) {
			throw (new Exception("Wyjście poza zakres tablicy: " + indexOutOfBoundsException.getMessage()));
		} catch (IOException iOException) {
			throw (new IOException("Błąd otwarcia pliku: " + iOException.getMessage()));
		} catch (Exception e) {
			throw (new Exception("Nieznany bład: " + e.getMessage()));
		} finally {
			if (bufferedReader != null)
				bufferedReader.close();
		}
	}
	
}
