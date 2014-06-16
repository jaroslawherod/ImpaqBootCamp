package com.contacts.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.contacts.repository.PersonRepository;
import com.contacts.web.CSVException;
import com.contacts.web.Person;

public class PersonServiceImpl implements PersonService {
	private PersonRepository repository;

	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public void createPerson(Person person) throws CSVException, IOException, SQLException {
		repository.createPerson(person);
	}

	@Override
	public List<Person> findAllPersons() throws CSVException, IOException, SQLException {
		return repository.findAllPersons();
	}
}
