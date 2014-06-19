package boot.camp.day1.hello;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class HelloWorld {

    public static void main(String[] args) {
	List<Person> people;

	Parser parser;
	Writer writer;
	try {
	    parser = new Parser();
	    writer = new Writer();

	    people = parser
		    .parse(new FileReader("src/test/resources/test.csv"));
	    writer.write(
		    parser.parse(new StringReader("Dawid,Chroscielski,20")),
		    new FileWriter(new File("src/writerTest.csv")));
	    writer.write(
		    parser.parse(new FileReader("src/test/resources/test.csv")),
		    new FileWriter(new File("src/writerTest2.csv")));

	    for (Person tmp : people) {
		System.out.println(tmp.getImie() + " " + tmp.getNazwisko()
			+ " " + tmp.getWiek());
	    }
	} catch (IOException e) {
	    System.out.println(e);
	} catch (ParserException e) {
	    System.out.println(e.getErrorName());
	} catch (WriterException e) {
	    System.out.println(e.getErrorName());
	}
    }

}