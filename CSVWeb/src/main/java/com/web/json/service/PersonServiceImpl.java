package com.web.json.service;

import java.io.IOException;
import java.util.List;

import com.web.json.CSVException;
import com.web.json.Person;
import com.web.json.repository.PersonRepository;

public class PersonServiceImpl implements PersonService {
	private PersonRepository repository;

	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public void createPerson(Person person) throws CSVException, IOException {
		repository.createPerson(person);
	}

	@Override
	public List<Person> findAllPersons() throws CSVException, IOException {
		return repository.findAllPersons();
	}
}
