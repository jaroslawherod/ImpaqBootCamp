package boot.camp.day1.hello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) throws IOException {
	String patch;
	Reader reader = null;
	
	try {
	    patch = "src//test//resources//sample.csv";
	    reader = new FileReader( new File(patch) );

	    Parser person = new Parser();
	    List<Container> l = person.parse(reader);

	} catch (FormatException exformat) {
	    System.out.println(exformat.getMessage());
	} catch (FileNotFoundException fileex) {
	    System.out.println("Nie znaleziono pliku. "
	    	+ "Sprawdź czy wprowadzona ścieżka jest poprawna: "
		    + fileex.getMessage());
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	} finally {
	    if (reader != null)
		reader.close();
	}

	
	
	
	/*
	 * wyswietlanie list (test sprawdzenia zawarosci listy) 
	 * for (Container x : l) { System.out.println("Id: " + x.getId());
	 * System.out.println("Name: " + x.getName());
	 * System.out.println("Address: " + x.getAddress());
	 * System.out.println(); }
	 */

    }
}
