package contacts;

import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.h2.jdbcx.JdbcDataSource;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import boot.camp.day1.hello.Person;

public class PersonServiceTest {
    public JdbcDataSource datasource;
    public Connection connection;
    
    @Before
    public void init() throws SQLException {
	datasource = new JdbcDataSource();
	datasource.setURL("jdbc:h2:mem:people");

	connection = datasource.getConnection();
    }
    
    @After
    public void deinit() throws SQLException {
	connection.close();
    }
    
    @Test
    public void shouldReturnNotEmptyListAfterAddingTestPerson() throws Exception {
	Statement stat = connection.createStatement();
	stat.execute("create table people(imie varchar(32) primary key, nazwisko varchar(64), wiek int)");
	stat.close();

	PersonServiceImpl personserviceimpl = new PersonServiceImpl(new DbPersonRepository(datasource));

	personserviceimpl.create(new Person("TEST", "TEST", 10));
	List<Person> people = personserviceimpl.findAll();

	assertThat(!people.isEmpty(), CoreMatchers.is(true));
    }
    
    @Test
    public void shouldReturnListWithTwoElementsEqualsToTestPeople()
	    throws Exception {
	Statement stat = connection.createStatement();
	stat.execute("create table people(imie varchar(32) primary key, nazwisko varchar(64), wiek int)");
	stat.close();

	PersonServiceImpl personserviceimpl = new PersonServiceImpl(new DbPersonRepository(datasource));

	Person firstPerson = new Person("TEST1", "TEST2", 10);
	Person secondPerson = new Person("TEST3", "TEST4", 20);

	personserviceimpl.create(firstPerson);
	personserviceimpl.create(secondPerson);

	List<Person> people = personserviceimpl.findAll();
	connection.close();

	assertThat(people.get(0).getImie().equals(firstPerson.getImie()),
		CoreMatchers.is(true));
	assertThat(people.get(0).getNazwisko()
		.equals(firstPerson.getNazwisko()), CoreMatchers.is(true));
	assertThat(people.get(0).getWiek() == firstPerson.getWiek(),
		CoreMatchers.is(true));

	assertThat(people.get(1).getImie().equals(secondPerson.getImie()),
		CoreMatchers.is(true));
	assertThat(
		people.get(1).getNazwisko().equals(secondPerson.getNazwisko()),
		CoreMatchers.is(true));
	assertThat(people.get(1).getWiek() == secondPerson.getWiek(),
		CoreMatchers.is(true));
    }
}