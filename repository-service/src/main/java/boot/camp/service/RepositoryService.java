package boot.camp.service;

import java.util.List;

import boot.camp.csv.model.Person;
import boot.camp.repository.CSVRepository;
import boot.camp.repository.IRepository;
import boot.camp.repository.RepositoryException;

public class RepositoryService {

	private IRepository repository;

	public RepositoryService(IRepository repository) {
		this.repository = repository;
	}

	public void savePerson(Person person) throws RepositoryServiceException {
		try {
			repository.savePerson(person);
		} catch (RepositoryException e) {
			throw new RepositoryServiceException(e);
		}
	}

	public List<Person> getPeople() throws RepositoryServiceException {
		try {
			return repository.getPeople();
		} catch (RepositoryException e) {
			throw new RepositoryServiceException(e);
		}
	}

}
