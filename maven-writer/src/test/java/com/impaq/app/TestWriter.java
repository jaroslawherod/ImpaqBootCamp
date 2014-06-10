package com.impaq.app;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class TestWriter {

    @Test
    public void TestCheckingForOutputContent() throws Exception {
	// zapis przykładowych danych do pliku
	List<Container> people = new ArrayList<Container>();
	Container c1 = new Container("a", "b", "c");
	people.add(c1);
	StringWriter sw = new StringWriter();
	CsvWriter save = new CsvWriter();
	save.savetofile(sw, people);

	// sprawdzenie czy wprowadzone dane sa poprawne
	assertThat(sw.toString(), is("a,b,c\n"));
    }

    @Test
    public void TestCheckForOutputFileContent() throws Exception {
	// zapis przykładowych danych do pliku
	List<Container> people = new ArrayList<Container>();
	Container c1 = new Container("a", "b", "c");
	people.add(c1);
	StringWriter sw = new StringWriter();
	CsvWriter save = new CsvWriter();
	save.savetofile(sw, people);

	// sprawdzenie czy linia zawiera trzy elementy
	String[] line = sw.toString().split(",");
	assertThat(true, is(line.length == 3));

    }

    @Test
    public void TestCheckCountOfLinesInOuput() throws Exception {
	List<Container> people = new ArrayList<Container>();
	Container c1 = new Container("a", "b", "c");
	people.add(c1);
	Container c2 = new Container("x", "y", "z");
	people.add(c2);
	StringWriter sw = new StringWriter();
	CsvWriter save = new CsvWriter();
	save.savetofile(sw, people);

	// sprawdzenie czy plik zawiera odpowiednia ilosc lini
	String[] lines = sw.toString().split("\n");
	assertThat(true, is(lines.length == people.size()));
    }

}
