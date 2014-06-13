package com.web.json.Service;

import java.io.IOException;
import java.util.List;

import com.web.json.CSVException;
import com.web.json.Person;
import com.web.json.Repository.Repository;


public class Service {
	private Repository repository;
	public Service(Repository repository){
		this.repository=repository;
	}
	
	public void savePerson(Person person) throws CSVException, IOException{
		repository.savePerson(person);
	}
	
	public List<Person> getPersons() throws CSVException, IOException{
		return repository.getPersons();
	}
}
