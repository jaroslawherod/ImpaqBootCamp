package com.contacts.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import day3.CSVException;
import day3.Person;

public interface PersonService {
	void createPerson(Person person) throws CSVException,
			IOException, SQLException;

	List<Person> findAllPersons() throws CSVException,
			IOException, SQLException;
}