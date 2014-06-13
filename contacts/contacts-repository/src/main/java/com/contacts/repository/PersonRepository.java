package com.contacts.repository;

import java.util.List;
import com.contacts.web.Person;


public interface PersonRepository {
	void createPerson(Person person);
	List<Person> findAllPersons();
}
