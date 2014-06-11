package boot.camp.repository;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.Before;
import org.junit.Test;

import boot.camp.csv.model.Person;

public class H2RepositoryTest {
	
	JdbcConnectionPool pool;
	private Connection connection;
	private H2Repository repository;
	Person person = new Person("x", "000000000", "adfgrgt");
	private String database  = "jdbc:h2:~/testtest";
	private String user = "sa"; 
	private String password = "";
	
	public H2RepositoryTest() throws ClassNotFoundException, SQLException, RepositoryException {
		Class.forName("org.h2.Driver");
		connection = DriverManager.getConnection(database, user, password);
		repository = new H2Repository(database, user, password);
	}
	
	@Before
	public void cleanUp() throws SQLException {
		
		PreparedStatement statement = connection.prepareStatement("DELETE from People where pesel=?");
		statement.setString(1, person.getPesel());
		statement.execute();
	}
	
	@Test
	public void insertTest() throws RepositoryException, SQLException {
		repository.savePerson(person);
		PreparedStatement statement = connection.prepareStatement("SELECT name, pesel, adress from People where pesel=?");
		statement.setString(1, person.getPesel()); 
		ResultSet results = statement.executeQuery();
		
		int expectedSize = 1;
		int actualSize= 0;
		
		while(results.next()) {
			actualSize++;
			Person insertedPerson = new Person(results.getString("name"),
					results.getString("pesel"), results.getString("adress"));
			assertEquals(person, insertedPerson);
		}
		assertEquals(expectedSize, actualSize);
	}
}
