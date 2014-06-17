package com.contacts.service;

import java.util.List;
import day3.Person;

public interface PersonService {
	void createPerson(Person person);
	List<Person> findAllPersons() ;
}