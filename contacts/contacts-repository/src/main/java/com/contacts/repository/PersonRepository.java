package com.contacts.repository;

import java.sql.SQLException;
import java.util.List;

import day3.Person;


public interface PersonRepository {
	void createPerson(Person person) throws SQLException;
	List<Person> findAllPersons() throws SQLException;
}
