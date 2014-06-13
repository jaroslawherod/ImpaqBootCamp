package repository.dbservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.DeleteDbFiles;

import repository.factory.Repository;
import contactscsvservice.Contact;

public class PersonWebAppH2Repository implements Repository, AutoCloseable {

	static final Logger logger = Logger.getLogger(PersonWebAppH2Repository.class);

	private JdbcConnectionPool jdbcConnectionPool;
	private Connection conn;
	
	public PersonWebAppH2Repository() throws Exception {
		
		Statement statement = null;
		try {
	        DeleteDbFiles.execute("~", "Contacts", true);
			this.jdbcConnectionPool = JdbcConnectionPool.create("jdbc:h2:~/Person", "sa", "sa");
			this.conn = jdbcConnectionPool.getConnection();
			
			statement = this.conn.createStatement();
			statement.execute( 
					"CREATE TABLE IF NOT EXISTS Contacts(ID INT NOT NULL auto_increment " +
					"PRIMARY KEY, Pesel VARCHAR(12), Name VARCHAR(255), Address VARCHAR(255))");
			statement.close();
			
		} catch (SQLException sqlException) {
			if (!this.conn.isClosed()) this.conn.close();
			this.jdbcConnectionPool.dispose();
			throw new RepositoryH2Exception("Błąd inicjalizacji bazy danych.", sqlException);
		} catch (Exception exception) {
			if (!this.conn.isClosed()) this.conn.close();
			this.jdbcConnectionPool.dispose();
			throw new RepositoryH2Exception("Nieznany błąd. ", exception);
		}
		finally {
			if(!statement.isClosed()) statement.close();
		}
	}

	public List<Contact> findAll() throws SQLException {

		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		
		Contact person = null;
		List<Contact> contactList = new ArrayList<Contact>();
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM Contacts");
			result = preparedStatement.executeQuery(); 
			
		    while ( result.next() ) {
		    	
		    	person = new Contact();
		    	person.setName( result.getString("Name") );
		    	person.setAddress( result.getString("Address") );
		    	person.setId( result.getString("Pesel") );
				contactList.add(person);
		    	
		    	//logger.info( result.getString("ID") );
		    }
			
		    return contactList;
		    
		} catch (RepositoryH2Exception repoException) {
			throw new RepositoryH2Exception("Błąd repozytorium.", repoException);
		} catch (SQLException sqlException) {
			throw new RepositoryH2Exception("Błąd SQL.", sqlException);
		} catch (Exception exception) {
			throw new RepositoryH2Exception("Nieznany błąd.", exception);
		} finally {
			this.jdbcConnectionPool.dispose();
		}

	}

	public void save(List<Contact> people) throws Exception {

		PreparedStatement statement = null;
		try {
			
			statement = conn.prepareStatement("INSERT INTO Contacts (Pesel, Name, Address) VALUES (?, ?, ?);");
			
			for( Contact i : people )
			{
				statement.setString(1, i.getId());
				statement.setString(2, i.getName());
				statement.setString(3, i.getAddress());
				statement.execute();
			}
			
		} catch (NullPointerException nullPointerException) {
			throw new RepositoryH2Exception("Nie wrowadzony żanych danych.", nullPointerException); 
		} catch (RepositoryH2Exception repositoryException) {
			throw new RepositoryH2Exception("Błąd repozytorium.", repositoryException);
		} catch (SQLException sqlException) {
			throw new RepositoryH2Exception("Błąd SQL.", sqlException);
		} catch (Exception exception) {
			throw new RepositoryH2Exception("Nieznany błąd.", exception);
		} finally {
			if(!statement.isClosed()) statement.close();	
		}
	}
	
	 @Override
     public void close() throws Exception {
		 
		if (!this.conn.isClosed()) this.conn.close();
			this.jdbcConnectionPool.dispose();
			
     }
	
}
