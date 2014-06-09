package com.impaq.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<Container> read(Reader reader) throws Exception {
	List<Container> personslist = new ArrayList<Container>();
	//BufferedReader in = new BufferedReader( new InputStreamReader( new FileInputStream( filedir ), "UTF8") );
	BufferedReader br = new BufferedReader(reader);
	try {
	    String line = "";
	    Container person;

	    while ((line = br.readLine()) != null) {
		person = new Container();
		String[] tmp = line.split(",");

		if (tmp.length != 3)
		    throw new FormatException();

		person.setName(tmp[0].trim());
		person.setId(tmp[1].trim());
		person.setAddress(tmp[2].trim());

		personslist.add(person);
	    }
	    return personslist;
	} catch (FormatException exformat) {
	    throw (new FormatException("Nieprawodłowy plik *.csv: "
		    + exformat.getMessage()));
	} catch (IndexOutOfBoundsException exarray) {
	    throw (new Exception("Wyjście poza zakres tablicy: "
		    + exarray.getMessage()));
	} catch (IOException exio) {
	    throw (new IOException("Błąd otwarcia pliku: " + exio.getMessage()));
	} catch (Exception e) {
	    throw (new Exception("Nieznany bład: " + e.getMessage()));
	} finally {
	    if (br != null)
		br.close();
	}

    }
}
