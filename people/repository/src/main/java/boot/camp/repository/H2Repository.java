package boot.camp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import boot.camp.csv.model.Person;
import boot.camp.repository.ConnectionPool;

public class H2Repository implements IRepository {

	private final String CREATE_TABLE_COMMAND = "CREATE TABLE IF NOT EXISTS People (id INT PRIMARY KEY AUTO_INCREMENT, name NVARCHAR(64), pesel VARCHAR(11), adress NVARCHAR(255))";
	private final String SELECT_COMMAND = "SELECT name, pesel, adress FROM People";
	private final String INSERT_COMMAND = "INSERT INTO People (name, pesel, adress) VALUES(?, ?, ?)";
	
	private ConnectionPool pool;
	
	public H2Repository(String url, String user, String password) throws RepositoryException {
		pool = new ConnectionPool(url, user, password);
		createTablePeople();
	}
	
	@Override
	public List<Person> getPeople() throws RepositoryException {
		Statement statement = null;
		Connection connection = null;
		List<Person> people = new ArrayList<Person>();
		try {
			connection = pool.getConnection();
			statement = connection.createStatement();
			ResultSet results = statement.executeQuery(SELECT_COMMAND);

			while (results.next()) {
				Person person = new Person(results.getString("name"),
						results.getString("pesel"), results.getString("adress"));
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
				finally {
					if(connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new RepositoryException(e);
						}
					}
				}
			}
		}
		return people;
	}

	@Override
	public void savePerson(Person person) throws RepositoryException {
		PreparedStatement statement = null;
		Connection connection = null;
		try {
			connection = pool.getConnection();
			statement = connection.prepareStatement(INSERT_COMMAND);
			statement.setString(1, person.getName());
			statement.setString(2, person.getPesel());
			statement.setString(3, person.getAdress());
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
				finally {
					if(connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new RepositoryException(e);
						}
					}
				}
			}
		}

	}

	private void createTablePeople() throws RepositoryException {
		Statement statement = null;
		Connection connection = null;
		try {
			connection = pool.getConnection();
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
				finally {
					if(connection != null) {
						try {
							connection.close();
						} catch (SQLException e) {
							throw new RepositoryException(e);
						}
					}
				}
			}
		}
	}

	@Override
	public void dispose() {
		pool.dispose();		
	}
}
