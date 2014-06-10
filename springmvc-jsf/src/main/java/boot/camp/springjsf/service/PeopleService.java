package boot.camp.springjsf.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.camp.csv.model.Person;
import boot.camp.service.RepositoryService;
import boot.camp.service.RepositoryServiceException;

@Service
public class PeopleService {
	
	@Autowired
	private RepositoryService repositoryService;
	
	
	public Collection<Person> getPeople() throws PeopleServiceException {
		try {
			return repositoryService.getPeople();
		} catch (RepositoryServiceException e) {
			throw new PeopleServiceException(e);
		}
	}
	
	public void savePerson(Person person) throws PeopleServiceException {
		try {
			repositoryService.savePerson(person);
		} catch (RepositoryServiceException e) {
			throw new PeopleServiceException(e);
		}
	}
}
