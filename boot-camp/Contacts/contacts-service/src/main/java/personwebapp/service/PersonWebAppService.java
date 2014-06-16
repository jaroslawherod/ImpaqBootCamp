package personwebapp.service;

import java.util.List;

import repository.factory.*;
import repository.csvservice.*;
import repository.dbservice.*;
import contactscsvservice.Contact;


public class PersonWebAppService {

	private Repository personRepository;
	
	public PersonWebAppService(Repository personRepository)	{
		this.personRepository = personRepository;
	}
	
	public List<Contact> findAll() throws ServiceException {

		List<Contact> people = null;
		try {
			
			people = personRepository.findAll();
			
		} catch (RepositoryH2Exception e) {
			throw new ServiceException("Bład repozytorum H2.", e);
		} catch (RepositoryCsvException e) {
			throw new ServiceException("Bład repozytorum CSV.", e);	
		} catch (Exception e) {
			throw new ServiceException("Nieznany błąd.", e);	
		}
		
		return people;
	}

	public void create(List<Contact> people) throws Exception {

		try {
			
			//List<Contact> fileContent = this.findAll(); 
			//this.validateData(people, fileContent); 
			
			personRepository.save(people);
			
			
			
		} catch (RepositoryH2Exception e) {
			throw new ServiceException("Bład repozytorum H2.", e);
		} catch (RepositoryCsvException e) {
			throw new ServiceException("Bład repozytorum CSV.", e);
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
