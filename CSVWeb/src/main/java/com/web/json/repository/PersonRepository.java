package com.web.json.repository;

import java.util.List;

import com.web.json.Person;

public interface PersonRepository {
	void createPerson(Person person);
	List<Person> findAllPersons();
}
