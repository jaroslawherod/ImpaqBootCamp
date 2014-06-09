package boot.camp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import boot.camp.csv.model.Person;

public class H2Repository implements IRepository {

	private final String CREATE_TABLE_COMMAND = "CREATE TABLE People IF NOT EXISTS (id INT PRIMARY KEY UNIQUE, name NVARCHAR(64), pesel NVARCHAR(11), adress NVARCHAR(255))";
	private final String SELECT_COMMAND = "SELECT * FROM People";
	private final String INSERT_COMMAND = "INSERT INTO People VALUES(?, ?, ?)";
	
	private Connection connection;
	
	public H2Repository(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public H2Repository() throws RepositoryException {
		createTablePeople();
	}

	@Override
	public List<Person> getPeople() throws RepositoryException {
		Statement statement = null;
		List<Person> people = new ArrayList<Person>();
		try {
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(SELECT_COMMAND);

			while (results.next()) {
				Person person = new Person(results.getInt("id"),
						results.getString("name"), results.getString("pesel"),
						results.getString("adress"));
				people.add(person);
			}
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} catch (NullPointerException e) {
			throw new RepositoryException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RepositoryException(e);
				}
			}
		}
		return people;
	}

	@Override
	public void savePerson(Person person) throws RepositoryException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_COMMAND);
			statement.setString(0,  person.getName());
			statement.setString(1,  person.getPesel());
			statement.setString(0,  person.getAdress());
			statement.execute();
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} catch (NullPointerException e) {
			throw new RepositoryException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RepositoryException(e);
				}
			}
		}

	}

	private void createTablePeople() throws RepositoryException {
		Statement statement = null;
		try {
			statement = connection.createStatement();
			statement.execute(CREATE_TABLE_COMMAND);
		} catch (SQLException e) {
			throw new RepositoryException(e);
		} catch (NullPointerException e) {
			throw new RepositoryException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					throw new RepositoryException(e);
				}
			}
		}
	}
}
