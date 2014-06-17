package com.contacts.service;

import java.sql.SQLException;
import java.util.List;
import com.contacts.repository.PersonRepository;
import day3.Person;

public class PersonServiceImpl implements PersonService {
	private PersonRepository repository;

	public PersonServiceImpl(PersonRepository repository) {
		this.repository = repository;
	}

	@Override
	public void createPerson(Person person) {
		try {
			repository.createPerson(person);
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Person> findAllPersons(){
		try {
			return repository.findAllPersons();
		} catch (SQLException e) {
			throw new ServiceException(e);
		}
	}
}
