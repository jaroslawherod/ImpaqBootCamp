package com.impaq.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class WriterMain {
	public WriterMain() throws IOException {
		String path, patchnewfile;
		Reader reader = null;
		CsvReader person = null;

		Writer wr = null;
		CsvWriter writer = null;

		try {
			path = "src//test//resources//sample.csv";
			patchnewfile = "src//test//resources//newcsv.csv";
			reader = new FileReader(new File(path));
			person = new CsvReader();
			List<Container> l = person.read(reader);

			File check = new File(patchnewfile);

			wr = new FileWriter(patchnewfile);
			writer = new CsvWriter();
			writer.savetofile(wr, l);

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
			if (wr != null)
				wr.close();
		}

	}
}
