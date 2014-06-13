package com.contacts.service;

import java.io.IOException;
import java.util.List;
import com.contacts.web.*;

public interface PersonService {

	void createPerson(Person person) throws CSVException,
			IOException;

	List<Person> findAllPersons() throws CSVException,
			IOException;

}