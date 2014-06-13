package com.web.json.service;

import java.io.IOException;
import java.util.List;

import com.web.json.CSVException;
import com.web.json.Person;

public interface PersonService {

	void createPerson(Person person) throws CSVException,
			IOException;

	List<Person> findAllPersons() throws CSVException,
			IOException;

}