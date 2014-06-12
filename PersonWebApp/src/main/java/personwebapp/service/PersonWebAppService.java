package personwebapp.service;

import java.util.List;

import repository.csvservice.PersonWebAppRepository;
import repository.csvservice.RepositoryExceptions;
import contactscsvservice.Contact;

public class PersonWebAppService {

	private PersonWebAppRepository personRepository;
	
	public PersonWebAppService(String path, PersonWebAppRepository personRepository)	{
		this.personRepository = personRepository;
	}
	
	public List<Contact> findAll() throws ServiceException {

		List<Contact> people = null;
		try {
			
			people = personRepository.findAll();
			
		} catch (RepositoryExceptions e) {
			throw new ServiceException("Bład repozytorum.", e);	
		} catch (Exception e) {
			throw new ServiceException("Nieznany błąd.", e);	
		}
		
		return people;
	}

	public void update(List<Contact> people) throws Exception {

		try {
			
			//List<Contact> fileContent = this.findAll(); 
			//this.validateData(people, fileContent); 
			
			personRepository.save(people);

		} catch (RepositoryExceptions e) {
			throw new ServiceException("Bład repozytorum.", e);
		} catch (ServiceException e) {
				throw new ServiceException("Bład serwisu.", e);
		} catch (Exception e) {
			throw new Exception("Nieznany błąd.", e);
		}
	}

	public void validateData(List<Contact> contactsFromReqiest,	List<Contact> contactsFromFileContent) 
			throws ServiceException {

		for (Contact i : contactsFromReqiest) {
			for (Contact j : contactsFromFileContent) {
				if (i.getId().equals(j.getId()))
					throw new ServiceException("Duplikacja danych.");
			}
		}
		
	}

}
