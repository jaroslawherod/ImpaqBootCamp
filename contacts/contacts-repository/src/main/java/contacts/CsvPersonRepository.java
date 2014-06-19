package contacts;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import boot.camp.day1.hello.Person;
import boot.camp.day1.hello.Writer;

public class CsvPersonRepository implements PersonRepository {
    @Override
    public Person save(Person p) throws IOException {
	List<Person> people = new ArrayList<Person>();
	people.add(p);

	Writer w = new Writer();
	String path = System.getProperty("user.home");
	File file = new File(path + "/database.csv");
	w.write(people, new FileWriter(file, true));

	return p;
    }

    @Override
    public List<Person> findAll() throws IOException {
	List<Person> people = new ArrayList<Person>();
	boot.camp.day1.hello.Parser p = new boot.camp.day1.hello.Parser();

	String path = System.getProperty("user.home");
	File file = new File(path + "/database.csv");
	people = p.parse(new FileReader(file));

	return people;
    }
}