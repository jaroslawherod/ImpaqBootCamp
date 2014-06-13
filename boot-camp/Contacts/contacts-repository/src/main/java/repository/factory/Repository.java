package repository.factory;

import java.util.List;

import contactscsvservice.Contact;

public interface Repository {
	
	public List<Contact> findAll() throws Exception;
		
	public void save(List<Contact> people) throws Exception; 
	
}
