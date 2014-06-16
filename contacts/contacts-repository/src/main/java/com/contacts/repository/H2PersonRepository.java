package com.contacts.repository;

import java.util.LinkedList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.contacts.web.Person;

public class H2PersonRepository implements PersonRepository{
	private final String createUserTable="CREATE TABLE IF NOT EXISTS Persons(id varchar(255), fullname varchar(255) PRIMARY KEY,address varchar(255))";
	private String createPerson="MERGE INTO Persons VALUES";
	private String findAllPersons="SELECT * FROM Persons";
	private Statement statement;	
	private Connection connection;
	public H2PersonRepository() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new RepositoryException("Brak sterownika bazy danych", e);
		}
		try{
			connection= DriverManager.getConnection("jdbc:h2:~/test");
		}
		catch(SQLException e){
			throw new RepositoryException("Problem z polaczeniem z baza danych", e);
		}
		try{
			statement=connection.createStatement();
			statement.execute(createUserTable);
		}
		catch(SQLException e){
			throw new RepositoryException("Problem z wykonaniem zapytania",e);
		}


	}
	
	@Override
	public void createPerson(Person person) {
		try {
			statement.execute(createPerson+"('"+person.getId()+"','"+person.getName()+"','"+person.getAdres()+"')");
		} catch (SQLException e) {
			throw new RepositoryException("Problem z wykonaniem zapytania do bazy danych",e);
		}
	}

	public void deletePersonsTable(){
		try {
			statement.execute("DROP TABLE IF EXISTS Persons CASCADE");
		} catch (SQLException e) {
			throw new RepositoryException("Nie mozna usunac tabeli Persons", e);
		}
	}
	
	@Override
	public List<Person> findAllPersons(){
		List<Person> personsList=new LinkedList<Person>();
		try{
		statement.execute(findAllPersons);
		ResultSet set=statement.getResultSet();
		while(set.next()){
			Person person=new Person();
			person.setId(set.getString("id"));
			person.setName(set.getString("fullname"));
			person.setAdres(set.getString("address"));
			personsList.add(person);
		}
		}
		catch(SQLException e){
			throw new RepositoryException("Problem z wykonaniem zapytania do bazy danych",e);
		}
		return personsList;
	}

}
