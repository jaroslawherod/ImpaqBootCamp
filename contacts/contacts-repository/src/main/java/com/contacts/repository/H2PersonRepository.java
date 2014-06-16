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
	private final String createUserTable="CREATE TABLE IF NOT EXISTS Persons(id varchar(255) primary key, fullname varchar(255),address varchar(255))";
	private String createPerson="INSERT INTO Persons VALUES";
	private String findAllPersons="SELECT * FROM Persons";
	private Statement statement;	
	public H2PersonRepository() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.driver");
		Connection connection= DriverManager.getConnection("jdbc:h2:~/test");
		statement=connection.createStatement();
		statement.executeQuery(createUserTable);
	}
	
	@Override
	public void createPerson(Person person) throws SQLException {
		statement.executeQuery(createPerson+"("+person.getId()+","+person.getName()+","+person.getAdres()+")");
	}

	@Override
	public List<Person> findAllPersons() throws SQLException {
		List<Person> personsList=new LinkedList<Person>();
		statement.executeQuery(findAllPersons);
		ResultSet set=statement.getResultSet();
		while(set.next()){
			Person person=new Person();
			person.setId(set.getString("id"));
			person.setName(set.getString("fullname"));
			person.setAdres(set.getString("address"));
			personsList.add(person);
		}
		return personsList;
	}

}
